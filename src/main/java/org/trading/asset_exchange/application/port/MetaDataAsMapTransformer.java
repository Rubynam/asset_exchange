package org.trading.asset_exchange.application.port;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.infrastruture.entity.MetaDataEntity;
import org.trading.asset_exchange.util.JsonUtil;

@Service
public class MetaDataAsMapTransformer implements Transformer<MetaDataEntity, Map<String, ?>>{

  @Override
  public Map<String, ?> transform(MetaDataEntity input) {
    Map<String,?> metadata = JsonUtil.jsonStringToMap(input.getMetadata());
    return metadata;
  }

  @Override
  public MetaDataEntity reverseTransform(Map<String, ?> stringMap) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
