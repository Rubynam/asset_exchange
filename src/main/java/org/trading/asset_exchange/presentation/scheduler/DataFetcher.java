package org.trading.asset_exchange.presentation.scheduler;

import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.application.command.PriceCommand;
import org.trading.asset_exchange.application.command.PublicExchangeFetcher;
import org.trading.asset_exchange.application.query.MetadataQuery;
import org.trading.asset_exchange.domain.SourceProvider;
import org.trading.asset_exchange.domain.aggregation.model.AggregatedPrice;
import org.trading.asset_exchange.infrastruture.config.ProviderConfig;
import org.trading.asset_exchange.infrastruture.config.ProviderConfig.ProviderEntry;
import org.trading.asset_exchange.presentation.response.MetadataResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataFetcher {
  private final PublicExchangeFetcher publicExchangeFetcher;
  private final ProviderConfig providerConfig;
  private final MetadataQuery metadataQuery;
  private final PriceCommand priceCommand;

  @Scheduled(fixedRateString = "${schedule.fixed-rate}")
  public void fetchData() throws Exception {
    List<AggregatedPrice> aggregatedPrices = new LinkedList<>();
    for (ProviderEntry entry : providerConfig.getProviderEntries()){
      List<AggregatedPrice> patchedData = publicExchangeFetcher.execute(entry.getName());
      log.debug("Fetched data for provider: {} data: {}",entry.getName(),patchedData);
      aggregatedPrices.addAll(patchedData);
    }
    priceCommand.execute(aggregatedPrices);
  }

  @Scheduled(fixedRateString = "${schedule.fixed-rate}")
  public void fetchDataByConfig() throws Exception {
    List<AggregatedPrice> aggregatedPrices = new LinkedList<>();
    for (MetadataResponse entry : metadataQuery.getMetaData()){
      String providerName = metadataQuery.getProviderNameOrDefault(entry.metadata(), SourceProvider.FXDS.getName());
      List<AggregatedPrice> patchedData = publicExchangeFetcher.execute(providerName);
      log.debug("Fetched data for provider: {} data: {} metadata {}",providerName,patchedData, entry.metadata());
      aggregatedPrices.addAll(patchedData);
    }
    priceCommand.execute(aggregatedPrices);
  }
}
