package org.trading.asset_exchange.domain.aggregation.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.trading.asset_exchange.domain.aggregation.model.PriceQueryContract;
import org.trading.asset_exchange.infrastruture.entity.PriceEntity;

public interface PriceService {

  Page<PriceEntity> fetchPrices(PriceQueryContract context);

  List<PriceEntity> saveAll(List<PriceEntity> entities);
}
