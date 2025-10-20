package org.trading.asset_exchange.application.command;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.domain.aggregation.service.MetaDataService;
import org.trading.asset_exchange.infrastruture.entity.MetaDataEntity;
import org.trading.asset_exchange.presentation.request.SymbolRequest;
import org.trading.asset_exchange.presentation.response.MetadataResponse;
import org.trading.asset_exchange.util.JsonUtil;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetaDataCommand implements Command<SymbolRequest, MetadataResponse>{

  private final MetaDataService metaDataService;

  @Override
  public MetadataResponse execute(SymbolRequest request) throws Exception {
    var map = request.toMap();
    var jsonMap = JsonUtil.mapToJsonString(map);
    var metaDataEntity = MetaDataEntity.builder()
        .metadata(jsonMap)
        .updatedAt(LocalDateTime.now())
        .build();
    var output = metaDataService.save(metaDataEntity);

    return new MetadataResponse(output.getId(),map,output.getUpdatedAt());
  }
}
