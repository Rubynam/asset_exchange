package org.trading.asset_exchange.application.command;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.trading.asset_exchange.domain.SourceProvider;
import org.trading.asset_exchange.domain.aggregation.model.AggregatedPrice;
import org.trading.asset_exchange.infrastruture.config.ProviderConfig;

@Service
@Slf4j
public class PublicExchangeFetcher implements Command<SourceProvider, List<AggregatedPrice>>{


  @Override
  public List<AggregatedPrice> execute(SourceProvider input) {
    switch (input) {
      case FXDS:
        return ;
      case CME:
        return null;
      default:
        throw new IllegalArgumentException("Invalid source provider");
    }
  }
}
