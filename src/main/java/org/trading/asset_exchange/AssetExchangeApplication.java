package org.trading.asset_exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AssetExchangeApplication {

  public static void main(String[] args) {
    SpringApplication.run(AssetExchangeApplication.class, args);
  }

}
