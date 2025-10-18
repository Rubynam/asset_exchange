package org.trading.asset_exchange.application.port;

import org.springframework.stereotype.Service;
import org.trading.asset_exchange.domain.aggregation.model.AggregatedPrice;
import org.trading.asset_exchange.infrastruture.mapper.FxdsData;

@Service
public class PublicExchangeDataTransformer implements Transformer<FxdsData,AggregatedPrice>{

  @Override
  public AggregatedPrice transform(FxdsData input) {
    return AggregatedPrice.builder()
        .baseCurrency(input.getBaseCurrency())
        .averageAsk(input.getAverageAsk())
        .averageBid(input.getAverageBid())
        .closeTime(input.getCloseTime())
        .highAsk(input.getHighAsk())
        .highBid(input.getHighBid())
        .lowAsk(input.getLowAsk())
        .lowBid(input.getLowBid())
        .quoteCurrency(input.getQuoteCurrency())
        .build();
  }

  @Override
  public FxdsData reverseTransform(AggregatedPrice output) {
    throwable: {
     throw new UnsupportedOperationException("Not supported yet.");
    }
  }
}
