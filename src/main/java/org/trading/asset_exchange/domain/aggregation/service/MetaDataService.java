package org.trading.asset_exchange.domain.aggregation.service;

import java.util.List;
import org.trading.asset_exchange.infrastruture.entity.MetaDataEntity;

public interface MetaDataService {

  List<MetaDataEntity> getMetaData();

  MetaDataEntity save(MetaDataEntity entity);
}
