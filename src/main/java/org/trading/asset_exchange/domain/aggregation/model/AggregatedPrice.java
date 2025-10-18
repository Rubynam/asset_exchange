package org.trading.asset_exchange.domain.aggregation.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

  @Override
  public String toString() {
    return "{\"AggregatedPrice\":{"
        + "        \"baseCurrency\":\"" + baseCurrency + "\""
        + ",         \"quoteCurrency\":\"" + quoteCurrency + "\""
        + ",         \"closeTime\":\"" + closeTime + "\""
        + ",         \"averageBid\":\"" + averageBid + "\""
        + ",         \"averageAsk\":\"" + averageAsk + "\""
        + ",         \"highBid\":\"" + highBid + "\""
        + ",         \"highAsk\":\"" + highAsk + "\""
        + ",         \"lowBid\":\"" + lowBid + "\""
        + ",         \"lowAsk\":\"" + lowAsk + "\""
        + "}}";
  }
}
