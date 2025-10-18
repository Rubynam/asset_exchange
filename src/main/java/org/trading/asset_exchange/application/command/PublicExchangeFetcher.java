package org.trading.asset_exchange.application.command;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.application.port.Transformer;
import org.trading.asset_exchange.domain.SourceProvider;
import org.trading.asset_exchange.domain.aggregation.service.Fetcher;
import org.trading.asset_exchange.domain.aggregation.service.FxdsApiFetcher;
import org.trading.asset_exchange.domain.aggregation.model.AggregatedPrice;
import org.trading.asset_exchange.infrastruture.config.ProviderConfig;
import org.trading.asset_exchange.infrastruture.config.ProviderConfig.ProviderEntry;
import org.trading.asset_exchange.infrastruture.mapper.FxdsData;
import org.trading.asset_exchange.util.DateTimeFormatterUtil;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicExchangeFetcher implements Command<String, List<AggregatedPrice>>{

  private final Map<String, Triple<SourceProvider,Fetcher,ProviderEntry>> FACTORY = new ConcurrentHashMap<>();
  private final ProviderConfig providerConfig;
  private final Transformer<FxdsData,AggregatedPrice> transformer;
  private final ApplicationContext context;

  @PostConstruct
  void init() {
    for( ProviderEntry entry : providerConfig.getProviderEntries() ) {
      SourceProvider sourceProvider = SourceProvider.getProviderByName(entry.getName());
      if(sourceProvider == null) {continue;}

      Fetcher fetcher = context.getBean(sourceProvider.getFetcherClass(), Fetcher.class);
      log.debug("Registered fetcher for provider: {} providerEntry {}",entry.getName(),entry);
      Triple<SourceProvider,Fetcher,ProviderEntry> triple = Triple.of(sourceProvider,fetcher,entry);
      FACTORY.put(entry.getName(), triple);
    }
  }

  @Override
  public List<AggregatedPrice> execute(String input) throws Exception {
    var triple = Optional.ofNullable(FACTORY.get(input))
        .orElseThrow(() -> new IllegalArgumentException("Invalid provider name: " + input));
    ProviderEntry entry = triple.getRight();

    SourceProvider provider = triple.getLeft();
    Fetcher fetcher = triple.getMiddle();
    updateStartDateAndEndDate(entry);

    switch (provider) {
      case FXDS:
        var fxdsPrices = ((FxdsApiFetcher) fetcher).fetchData(entry.getUrl(),
            entry.getParameters());
        return fxdsPrices.stream()
            .map(transformer::transform)
            .toList();
      default:
        throw new IllegalStateException("Unexpected value: " + provider);
    }
  }

  private void updateStartDateAndEndDate(ProviderEntry entry) {
    //noted update start_date and end_date
    //end_date is day now formatted YYYY-MM-DD
    LocalDate endDate = DateTimeFormatterUtil.now();
    entry.getParameters().put("end_date", DateTimeFormatterUtil.getFormattedDate(endDate, DateTimeFormatterUtil.YYYY_MM_DD));
    LocalDate startDate = endDate.minusDays(1);
    entry.getParameters().put("start_date", DateTimeFormatterUtil.getFormattedDate(startDate, DateTimeFormatterUtil.YYYY_MM_DD));
  }
}
