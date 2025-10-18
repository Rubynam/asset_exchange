package org.trading.asset_exchange.domain.aggregation;

import java.sql.Wrapper;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.trading.asset_exchange.infrastruture.mapper.FxdsData;

@Service("FxdsApiFetcher")
public class FxdsApiFetcher extends Fetcher {

  public FxdsApiFetcher(RestTemplate restTemplate) {
    super(restTemplate);
  }

  public List<FxdsData> fetchData(String url, Map<String,String> parameter) throws Exception {
    HttpEntity<Void> requestEntity = (HttpEntity<Void>) HttpEntity.EMPTY; // or add headers if needed

    return super.<FxdsDataWrapper>fetch(
            url,
            HttpMethod.GET,
            requestEntity,
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
