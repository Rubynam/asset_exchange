package org.trading.asset_exchange.application.query;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.application.port.MetaDataAsMapTransformer;
import org.trading.asset_exchange.domain.aggregation.service.MetaDataService;
import org.trading.asset_exchange.infrastruture.config.ProviderConfig.ProviderEntry;
import org.trading.asset_exchange.presentation.response.MetadataResponse;

@Service
@RequiredArgsConstructor
public class MetadataQuery {

  private final MetaDataService metaDataService;
  private final MetaDataAsMapTransformer metaDataAsMapTransformer;

  public List<MetadataResponse> getMetaData() {
    var entityList = metaDataService.getMetaData();
    return entityList.stream().map(item -> {
      var json = metaDataAsMapTransformer.transform(item);
      return new MetadataResponse(item.getId(), json, item.getUpdatedAt());
    }).toList();
  }

  @SuppressWarnings( "unchecked")
  public List<ProviderEntry> getProviderEntries() {
    return metaDataService.getMetaData().stream().map(item -> {
      Map<String, ?> json = metaDataAsMapTransformer.transform(item);
      var providerEntry = new ProviderEntry();
      providerEntry.setName(getProviderNameOrDefault(json, "FXDS"));
      if (json.containsKey("url")) {
        providerEntry.setUrl(json.get("url").toString());
      }
      providerEntry.setParameters((Map<String, String>) json);
      return providerEntry;
    }).toList();
  }

  public String getProviderNameOrDefault(Map<String, ?> metadata, String defaultProviderName) {
    if (metadata.containsKey("provider")) {
      return metadata.get("provider").toString();
    }
    return defaultProviderName;
  }
}
