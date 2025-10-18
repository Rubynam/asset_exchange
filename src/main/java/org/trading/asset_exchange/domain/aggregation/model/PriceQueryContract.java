package org.trading.asset_exchange.domain.aggregation.model;

public interface PriceQueryContract {

  String getBaseCurrency();
  String getQuoteCurrency();
  int getPage();
  int getSize();
}
