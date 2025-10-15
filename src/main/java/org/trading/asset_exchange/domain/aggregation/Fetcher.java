package org.trading.asset_exchange.domain.aggregation;


import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;
import org.trading.asset_exchange.infrastruture.mapper.FxdsData;

@Slf4j
public abstract class Fetcher {

  protected final RestTemplate restTemplate;

  public Fetcher(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public abstract FxdsData fetchData() throws Exception;

  protected  <T>T fetch(String url, HttpMethod method,
      @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) throws Exception{
    ResponseEntity<T> responseEntity =  restTemplate.exchange(url, method,requestEntity,responseType,uriVariables);
    if(validationResponse(responseEntity))
      return responseEntity.getBody();
    throw new Exception("Response validation failed");
  }

  protected abstract boolean validationResponse(ResponseEntity<?> responseEntity);
}
