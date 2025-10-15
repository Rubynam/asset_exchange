package org.trading.asset_exchange.domain.aggregation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.trading.asset_exchange.infrastruture.mapper.FxdsData;

@Service
public class FxdsApiFetcher extends Fetcher{

  public FxdsApiFetcher(RestTemplate restTemplate) {
    super(restTemplate);
  }

  @Override
  public FxdsData fetchData() throws Exception {
    return super.fetch("",null,null, null,null);
  }

  @Override
  protected boolean validationResponse(ResponseEntity<?> responseEntity) {
    return false;
  }
}
