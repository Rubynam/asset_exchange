package org.trading.asset_exchange.application.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.domain.aggregation.service.CurrencyManagementService;

@Service
@RequiredArgsConstructor
public class CurrencyCommand implements Command<String, Void> {

  private final CurrencyManagementService currencyManagementService;

  @Override
  public Void execute(String input) throws Exception {
    return currencyManagementService.deleteCurrency(input);
  }
}
