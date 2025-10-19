package org.trading.asset_exchange.infrastruture.config;

import lombok.extern.slf4j.Slf4j;
import org.zalando.logbook.BodyFilter;
import org.zalando.logbook.Logbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.core.Conditions;
import org.zalando.logbook.core.DefaultHttpLogFormatter;
import org.zalando.logbook.core.DefaultHttpLogWriter;
import org.zalando.logbook.core.DefaultSink;


@Configuration
@Slf4j
public class LogbookConfig {

  @Bean
  public Logbook logbook() {
    return Logbook.builder()
        .condition(Conditions.exclude(Conditions.requestTo("/api/health"),
            Conditions.contentType("application/octet-stream"),
            Conditions.header("X-Secret", "true")))
        .sink(new DefaultSink(new DefaultHttpLogFormatter(), new DefaultHttpLogWriter()))
        .build();
  }

  @Bean
  public BodyFilter authorizationRedactor() {
    return (body, context) -> {

      return body;
    };
  }


}
