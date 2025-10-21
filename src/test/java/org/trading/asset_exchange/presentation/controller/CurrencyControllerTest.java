package org.trading.asset_exchange.presentation.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.trading.asset_exchange.BaseComponentTest;
import org.trading.asset_exchange.PageModule;
import org.trading.asset_exchange.presentation.response.MetadataResponse;

class CurrencyControllerTest extends BaseComponentTest {

  @Test
  void getCurrencies() throws Exception {
    MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/currency")
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    int status = result.getResponse().getStatus();
    assertThat(status).isEqualTo(200);

    String content = result.getResponse().getContentAsString();
    assertThat(content).isNotNull();


    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new PageModule());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    Page<String> page = objectMapper.readValue(content, new com.fasterxml.jackson.core.type.TypeReference<PageImpl<String>>() {});

    assertThat(page.getTotalElements()).isEqualTo(1);
    assertThat(page.getContent().get(0)).isEqualTo("EUR");
  }

  @Test
  void createMetaData() throws Exception {
    MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.post("/currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                    + "    \"base\": \"JPY\",\n"
                    + "    \"quote\": \"USD\",\n"
                    + "    \"data_type\": \"chart\"\n"
                    + "}")
        )
        .andReturn();

    String content = result.getResponse().getContentAsString();
    assertThat(content).isNotNull();

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    MetadataResponse metadataResponse = objectMapper.readValue(content, new com.fasterxml.jackson.core.type.TypeReference<MetadataResponse>() {});

    assertEquals(1L,metadataResponse.id());
    Map<String,?> metadata = metadataResponse.metadata();

    assertEquals("JPY",metadata.get("base"));
    assertEquals("USD",metadata.get("quote"));
  }

  @Test
  void deleteMetaData() throws Exception {
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


    MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.delete("/currency/JPY")
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andReturn();

    int status = result.getResponse().getStatus();
    assertThat(status).isEqualTo(200);
  }
}