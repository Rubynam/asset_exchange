package org.trading.asset_exchange.presentation.controller;


import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.trading.asset_exchange.BaseComponentTest;
import org.trading.asset_exchange.presentation.response.PriceResponse;
import org.trading.asset_exchange.presentation.scheduler.DataFetcher;

class PriceControllerTest extends BaseComponentTest {

  @Autowired
  private DataFetcher fetcherData;

  @Test
  void givenMockMvc_whenFetcherFetchData_thenReturnPage() throws Exception {
    Awaitility.await()
        .atMost(10, TimeUnit.SECONDS)
        .pollDelay(1, TimeUnit.SECONDS)
        .pollInterval(1, TimeUnit.SECONDS)
        .until(() -> {
          try {
            ParameterizedTypeReference<Page<PriceResponse>> responseType = new ParameterizedTypeReference<Page<PriceResponse>>() {};
            MvcResult result = mockMvc.perform(
                    MockMvcRequestBuilders.get("/v1/price?base=EUR&quote=USD")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
            int status = result.getResponse().getStatus();

            String content = result.getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            PricePageDto dto = mapper.readValue(content, PricePageDto.class);
            Page<PriceResponse> page = new PageImpl<>(
                dto.content,
                PageRequest.of(dto.number, dto.size),
                dto.totalElements
            );

            return status == 200 && !page.isEmpty();
          } catch (Exception e) {
            return false;
          }
        });

    // Step 2: After the first successful API call post-scheduler, perform a normal GET to verify.
    try {
      MvcResult result = mockMvc.perform(
              MockMvcRequestBuilders.get("/v1/price?base=EUR&quote=USD")
                  .contentType(MediaType.APPLICATION_JSON))
          .andReturn();

      int status = result.getResponse().getStatus();
      String content = result.getResponse().getContentAsString();

      // Assertions
      assertThat(status).isEqualTo(200);
      assertThat(content).isNotNull();
      assertThat(content.trim()).isNotEmpty();
    } catch (Exception e) {
      throw new AssertionError("GET after scheduler run failed: " + e.getMessage(), e);
    }

  }


  static class PricePageDto {
    public int number;
    public int size;
    public long totalElements;
    public List<PriceResponse> content;

  }
}