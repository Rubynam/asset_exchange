package org.trading.asset_exchange.application.command;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.trading.asset_exchange.application.port.PriceTransformer;
import org.trading.asset_exchange.domain.aggregation.model.AggregatedPrice;
import org.trading.asset_exchange.domain.aggregation.service.PriceService;
import org.trading.asset_exchange.infrastruture.entity.PriceEntity;

@Service
@RequiredArgsConstructor
public class PriceCommand implements Command<List<AggregatedPrice>, Void> {

  private final PriceService priceService;
  private final PriceTransformer priceTransformer;

  @Override
  public Void execute(List<AggregatedPrice> input) throws Exception {
    if (CollectionUtils.isEmpty(input)) {
      return null;
    }
    List<PriceEntity> priceEntities = input.stream()
        .map(priceTransformer::transform)
        .toList();
    priceService.saveAll(priceEntities);
    return null;
  }
}
