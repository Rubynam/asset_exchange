package org.trading.asset_exchange.presentation.controller;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.trading.asset_exchange.application.command.CurrencyCommand;
import org.trading.asset_exchange.application.query.CurrencyQuery;

@Slf4j
@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyQuery currencyQuery;
  private final CurrencyCommand currencyCommand;

  @GetMapping
  public Page<String> getCurrencies(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size) {
    return currencyQuery.getCurrencies(page, size);
  }

  @DeleteMapping
  public void deleteCurrency(@RequestParam @Nonnull String currency) throws Exception {
    log.warn("Delete currency not supported yet {}",currency);
    currencyCommand.execute(currency);
  }
}
