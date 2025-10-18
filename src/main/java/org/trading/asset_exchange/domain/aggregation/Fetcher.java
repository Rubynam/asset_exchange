package org.trading.asset_exchange.domain.aggregation;


import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

@Slf4j
public abstract class Fetcher {

  protected final RestTemplate restTemplate;


  public Fetcher(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }


  protected <Wrapper>Wrapper fetch(String url, HttpMethod method,
      @Nullable HttpEntity<?> requestEntity, Class<Wrapper> responseType, Map<String, ?> uriVariables) throws Exception{
    ResponseEntity<Wrapper> responseEntity =  restTemplate.exchange(url, method,requestEntity,responseType,uriVariables);
    if(validationResponse(responseEntity))
      return responseEntity.getBody();
    throw new Exception("Response validation failed");
  }


  protected abstract boolean validationResponse(ResponseEntity<?> responseEntity);
}
