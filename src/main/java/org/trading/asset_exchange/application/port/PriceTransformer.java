package org.trading.asset_exchange.application.port;

import org.springframework.stereotype.Service;
import org.trading.asset_exchange.domain.aggregation.model.AggregatedPrice;
import org.trading.asset_exchange.infrastruture.entity.PriceEntity;

@Service
public class PriceTransformer implements Transformer<AggregatedPrice, PriceEntity> {

  @Override
  public PriceEntity transform(AggregatedPrice input) {
    return PriceEntity.builder()
        .baseCurrency(input.getBaseCurrency())
        .quoteCurrency(input.getQuoteCurrency())
        .highBid(input.getHighBid())
        .highAsk(input.getHighAsk())
        .lowBid(input.getLowBid())
        .lowAsk(input.getLowAsk())
        .closeTime(input.getCloseTime())
        .averageAsk(input.getAverageAsk())
        .averageBid(input.getAverageBid())
        .build();
  }

  @Override
  public AggregatedPrice reverseTransform(PriceEntity output) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
