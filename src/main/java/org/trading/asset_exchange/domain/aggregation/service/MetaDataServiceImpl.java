package org.trading.asset_exchange.domain.aggregation.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.infrastruture.entity.MetaDataEntity;
import org.trading.asset_exchange.infrastruture.repository.MetaDataRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MetaDataServiceImpl implements MetaDataService{

  private final MetaDataRepository metaDataRepository;

  @Override
  public List<MetaDataEntity> getMetaData() {
    return metaDataRepository.findAll();
  }

  @Override
  public MetaDataEntity save(MetaDataEntity entity) {
    return metaDataRepository.save(entity);
  }
}
