package org.trading.asset_exchange.domain.aggregation.model;


import lombok.Data;

/**
 {
 "base_currency": "EUR",
 "quote_currency": "USD",
 "close_time": "2025-03-26T23:59:59Z",
 "average_bid": "1.07764",
 "average_ask": "1.07780",
 "high_bid": "1.08020",
 "high_ask": "1.08035",
 "low_bid": "1.07322",
 "low_ask": "1.07338"
 }
 */
@Data
public class AggregatedPrice {

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
