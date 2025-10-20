package org.trading.asset_exchange.domain.aggregation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.infrastruture.repository.PriceRepository;

@Service
@RequiredArgsConstructor
public class CurrencyManagementServiceImpl implements CurrencyManagementService {

  private final PriceRepository priceRepository;

  @Override
  public Page<String> getCurrencies(int page, int size) {
    return priceRepository.findAllBaseCurrencies(PageRequest.of(page, size));
  }

  @Override
  public Void deleteCurrency(String baseCurrency) {
    priceRepository.deleteByBaseCurrency(baseCurrency);
    return null;
  }
}
