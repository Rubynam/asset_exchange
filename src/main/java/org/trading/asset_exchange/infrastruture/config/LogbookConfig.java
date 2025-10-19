package org.trading.asset_exchange.infrastruture.config;

import org.zalando.logbook.BodyFilter;
import org.zalando.logbook.Logbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LogbookConfig {

  @Bean
  public Logbook logbook() {
    return Logbook.builder()
        .condition(request -> true)
        .build();

  }

  @Bean
  public BodyFilter authorizationRedactor() {
    return (body, context) -> {
      return body;
    };
  }


}
