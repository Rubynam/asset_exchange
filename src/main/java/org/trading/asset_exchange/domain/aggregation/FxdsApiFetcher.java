package org.trading.asset_exchange.domain.aggregation;

import java.sql.Wrapper;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.trading.asset_exchange.infrastruture.mapper.FxdsData;

@Service
public class FxdsApiFetcher extends Fetcher {

  public FxdsApiFetcher(RestTemplate restTemplate) {
    super(restTemplate);
  }

  public List<FxdsData> fetchData(String url, Map<String,String> parameter) throws Exception {
    return super.<FxdsDataWrapper>fetch(
            url,
            HttpMethod.GET,
            null,
            FxdsDataWrapper.class,
            parameter
        ).getResponse();
  }

  @Override
  protected boolean validationResponse(ResponseEntity<?> responseEntity) {
    return responseEntity.getStatusCode().is2xxSuccessful();
  }

  @Getter
  @Setter
  static class FxdsDataWrapper {

    private List<FxdsData> response;
  }
}
