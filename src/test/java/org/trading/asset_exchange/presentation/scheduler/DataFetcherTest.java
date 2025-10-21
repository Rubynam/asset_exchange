package org.trading.asset_exchange.presentation.scheduler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.TimeUnit;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.trading.asset_exchange.BaseComponentTest;
import org.trading.asset_exchange.presentation.response.PriceResponse;

class DataFetcherTest extends BaseComponentTest {

  @MockitoSpyBean
  private DataFetcher fetcherData;

  @Test
  void givenCallingAPI_whenCallingApiSuccess_thenFetchedData() throws Exception {

    MvcResult createdResult = mockMvc.perform(
            MockMvcRequestBuilders.post("/currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                    + "    \"base\": \"JPY\",\n"
                    + "    \"quote\": \"USD\",\n"
                    + "    \"data_type\": \"chart\"\n"
                    + "}")
        )
        .andReturn();
    assertThat(createdResult.getResponse().getStatus()).isEqualTo(202);

    Awaitility.await()
        .atMost(20, TimeUnit.SECONDS)
        .pollDelay(1, TimeUnit.SECONDS)
        .pollInterval(1, TimeUnit.SECONDS)
        .until(() -> {
          try {
            try{
              MvcResult result = mockMvc.perform(
                      MockMvcRequestBuilders.get("/v1/price?base=JPY&quote=USD")
                          .contentType(MediaType.APPLICATION_JSON))
                  .andReturn();
              Assertions.assertEquals(200, result.getResponse().getStatus());

              String content = result.getResponse().getContentAsString();

              ObjectMapper mapper = new ObjectMapper();
              mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
              PricePageDto dto = mapper.readValue(content, PricePageDto.class);
              Page<PriceResponse> page = new PageImpl<>(
                  dto.content,
                  PageRequest.of(dto.number, dto.size),
                  dto.totalElements
              );

              if(!page.isEmpty()){
                Mockito.verify(fetcherData,Mockito.atLeastOnce()).fetchDataByConfig();
                return true;
              }

            }catch (Exception e){
              return false;
            }
            return false;
          } catch (Exception e) {
            return false;
          }
        });
  }
}