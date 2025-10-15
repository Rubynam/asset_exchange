package org.trading.asset_exchange.infrastruture.mapper;

import lombok.Data;

@Data
public class FxdsData {

  private String baseCurrency;
  private String quoteCurrency;
  private String closeTime;
  private String averageBid;
  private String averageAsk;
  private String highBid;
  private String highAsk;
  private String lowBid;
  private String lowAsk;
}
