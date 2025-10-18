package org.trading.asset_exchange.infrastruture.config;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "providers")
@Getter
@Setter
public class ProviderConfig {

  private List<ProviderEntry> providerEntries;


  @Getter
  @Setter
  public static class ProviderEntry{
    private String name;
    private String url;
    private Map<String, String> parameters;

    @Override
    public String toString() {
      return "ProviderEntry{" +
          "name='" + name + '\'' +
          ", url='" + url + '\'' +
          '}';
    }
  }
}
