package org.trading.asset_exchange.infrastruture.config;

import java.util.List;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.trading.asset_exchange.domain.SourceProvider;

@Configuration
@ConfigurationProperties(prefix = "providers")
@Getter
public class ProviderConfig {

  private List<SourceProvider> providers;
}
