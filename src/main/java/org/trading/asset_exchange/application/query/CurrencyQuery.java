package org.trading.asset_exchange.application.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.domain.aggregation.service.CurrencyManagementService;

@Service
@RequiredArgsConstructor
public class CurrencyQuery {

  private final CurrencyManagementService currencyManagementService;

  public Page<String> getCurrencies(int page, int size) {
    return currencyManagementService.getCurrencies(page, size);
  }
}
