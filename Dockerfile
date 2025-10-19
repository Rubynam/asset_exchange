FROM openjdk:17-jdk-slim AS builder

ENV GRADLE_OPTS="-Xmx1g"

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY settings.gradle .
COPY build.gradle .
COPY src src
RUN chmod +x gradlew

# Build the application (skip tests for faster image builds; remove -x test if you want tests)
RUN ./gradlew build -x test

# ---- Runner stage: run the built jar ----
FROM eclipse-temurin:17-jre-jammy AS runner

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
