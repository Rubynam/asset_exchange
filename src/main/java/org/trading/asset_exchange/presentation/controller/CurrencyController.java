package org.trading.asset_exchange.presentation.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.trading.asset_exchange.application.command.CurrencyCommand;
import org.trading.asset_exchange.application.command.MetaDataCommand;
import org.trading.asset_exchange.application.query.CurrencyQuery;
import org.trading.asset_exchange.presentation.request.SymbolRequest;
import org.trading.asset_exchange.presentation.response.MetadataResponse;

@Slf4j
@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyQuery currencyQuery;
  private final CurrencyCommand currencyCommand;
  private final MetaDataCommand metaDataCommand;

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

  @PostMapping
  public ResponseEntity<MetadataResponse> createMetaData(
      @RequestBody SymbolRequest symbolRequest)
      throws Exception {
    return new ResponseEntity<>(metaDataCommand.execute(symbolRequest), HttpStatus.ACCEPTED);
  }
}
