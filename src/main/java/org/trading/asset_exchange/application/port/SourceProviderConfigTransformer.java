package org.trading.asset_exchange.application.port;

import org.springframework.stereotype.Service;
import org.trading.asset_exchange.domain.aggregation.model.SourceProviderParams;
import org.trading.asset_exchange.infrastruture.config.ProviderConfig.ProviderEntry;

@Service
public class SourceProviderConfigTransformer implements Transformer<ProviderEntry, SourceProviderParams>{

  @Override
  public SourceProviderParams transform(ProviderEntry input) {
    return SourceProviderParams.builder()
        .name(input.getName())
        .url(input.getUrl())
        .params(input.getParameters())
        .build();
  }

  @Override
  public ProviderEntry reverseTransform(SourceProviderParams output) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
