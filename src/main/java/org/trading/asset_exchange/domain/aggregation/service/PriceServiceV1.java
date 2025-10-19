package org.trading.asset_exchange.domain.aggregation.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trading.asset_exchange.domain.aggregation.model.PriceQueryContract;
import org.trading.asset_exchange.infrastruture.entity.PriceEntity;
import org.trading.asset_exchange.infrastruture.repository.PriceRepository;

@Service( "PriceServiceV1")
@Slf4j
@RequiredArgsConstructor
public class PriceServiceV1 implements PriceService{

  private final PriceRepository priceRepository;

  @Override
  public Page<PriceEntity> fetchPrices(PriceQueryContract context) {
    Pageable pageable = PageRequest.of(context.getPage(), context.getSize());
    return priceRepository.findAllByBaseCurrencyAndQuoteCurrency(context.getBaseCurrency(), context.getQuoteCurrency(), pageable);
  }

  @Override
  @Transactional
  public List<PriceEntity> saveAll(List<PriceEntity> entities) {
    return priceRepository.saveAll(entities);
  }
}
