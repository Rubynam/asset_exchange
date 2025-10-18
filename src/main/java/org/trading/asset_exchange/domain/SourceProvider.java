package org.trading.asset_exchange.domain;

import lombok.Getter;
import org.trading.asset_exchange.domain.aggregation.service.FxdsApiFetcher;
import org.trading.asset_exchange.infrastruture.config.ProviderConfig.ProviderEntry;

@Getter
public enum SourceProvider {
  FXDS("FXDS", FxdsApiFetcher.class.getSimpleName(), ProviderEntry.class),
  CME("CME", null,ProviderEntry.class);

  private final String name;
  private final String fetcherClass;
  private final Class<?> configClass;

  private SourceProvider(String name, String fetcherClass, Class<?> configClass) {
    this.name = name;
    this.fetcherClass = fetcherClass;
    this.configClass = configClass;
  }

  public static SourceProvider getProviderByName(String name) {
    for (SourceProvider provider : SourceProvider.values()) {
      if (provider.name.equalsIgnoreCase(name)) {
        return provider;
      }
    }
    return null;
  }
}
