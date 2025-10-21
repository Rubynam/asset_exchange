package org.trading.asset_exchange.domain.aggregation.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.infrastruture.entity.PriceEntity;
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
  @Transactional
  public Void deleteCurrency(String baseCurrency) {
    List<PriceEntity> priceEntities = priceRepository.findPriceEntitiesByBaseCurrency(baseCurrency);
    priceRepository.deleteAll(priceEntities);
    return null;
  }
}
