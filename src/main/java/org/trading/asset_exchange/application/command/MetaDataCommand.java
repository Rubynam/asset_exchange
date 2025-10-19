package org.trading.asset_exchange.application.command;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.application.port.MetaDataAsMapTransformer;
import org.trading.asset_exchange.domain.aggregation.service.MetaDataService;
import org.trading.asset_exchange.infrastruture.entity.MetaDataEntity;
import org.trading.asset_exchange.presentation.response.MetadataResponse;
import org.trading.asset_exchange.util.JsonUtil;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetaDataCommand implements Command<Map<String,?>, MetadataResponse>{

  private final MetaDataService metaDataService;
  private final MetaDataAsMapTransformer metaDataAsMapTransformer;

  @Override
  public MetadataResponse execute(Map<String, ?> input) throws Exception {
    var jsonMap = JsonUtil.mapToJsonString(input);
    var metaDataEntity = MetaDataEntity.builder()
        .metadata(jsonMap)
        .updatedAt(LocalDateTime.now())
        .build();
    var output = metaDataService.save(metaDataEntity);

    return new MetadataResponse(output.getId(),input,output.getUpdatedAt());
  }
}
