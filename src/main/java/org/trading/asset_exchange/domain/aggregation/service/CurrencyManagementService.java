package org.trading.asset_exchange.domain.aggregation.service;

import org.springframework.data.domain.Page;

public interface CurrencyManagementService {

  Page<String> getCurrencies(int page, int size);

  Void deleteCurrency(String currency);
}
