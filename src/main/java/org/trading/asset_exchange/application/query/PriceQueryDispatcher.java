package org.trading.asset_exchange.application.query;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.trading.asset_exchange.application.port.PriceResponseTransformer;
import org.trading.asset_exchange.domain.aggregation.model.PriceQueryV1;
import org.trading.asset_exchange.domain.aggregation.service.PriceService;
import org.trading.asset_exchange.domain.aggregation.service.PriceServiceV1;
import org.trading.asset_exchange.infrastruture.entity.PriceEntity;
import org.trading.asset_exchange.presentation.response.PriceResponse;

@Component
@RequiredArgsConstructor
public class PriceQueryDispatcher {

  private final Map<Integer, Class<? extends PriceService>> FACTORY = new ConcurrentHashMap<>();
  private final ApplicationContext context;
  private final PriceResponseTransformer transformer;

  @PostConstruct
  void init() {
    FACTORY.put(1, PriceServiceV1.class);
  }

  public Page<PriceResponse> dispatch(int version, Map<String, String> params) {
    switch (version) {
      case 1:
        PriceQueryV1 input =  PriceQueryV1.builder()
            .baseCurrency(params.get("baseCurrency"))
            .quoteCurrency(params.get("quoteCurrency"))
            .page(Integer.parseInt(params.get("page")))
            .size(Integer.parseInt(params.get("size")))
            .build();
        return queryV1(input);
      default:
        throw new IllegalArgumentException("Unsupported PriceQuery version: " + version);
    }
  }


  Page<PriceResponse> queryV1(PriceQueryV1 input) {
    Page<PriceEntity> data = FACTORY.get(1).cast(context.getBean(FACTORY.get(1))).fetchPrices(input);
    return data.map(transformer::transform);
  }
}
