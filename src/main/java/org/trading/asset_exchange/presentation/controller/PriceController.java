package org.trading.asset_exchange.presentation.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.trading.asset_exchange.application.query.PriceQueryDispatcher;
import org.trading.asset_exchange.presentation.response.PriceResponse;

@RestController
@RequestMapping("/v{version}/price")
@RequiredArgsConstructor
public class PriceController {

  private final PriceQueryDispatcher priceQueryDispatcher;


  @GetMapping
  public Page<PriceResponse> getPrices(@PathVariable("version") String version,
      @RequestParam(required = true) String baseCurrency,
      @RequestParam(required = true) String quoteCurrency,
      @RequestParam(required = true,defaultValue = "0") int page,
      @RequestParam(required = true,defaultValue = "10") int size) {
    //todo need to refactor code
    return priceQueryDispatcher.dispatch(Integer.parseInt(version),
        Map.of("baseCurrency", baseCurrency,"quoteCurrency",quoteCurrency, "page", String.valueOf(page), "size", String.valueOf(size))
    );
  }

  @PutMapping(path = "/{quote}")
  public void updatePrice(@PathVariable("version") String version, @PathVariable("quote") String quote) {

  }
}
