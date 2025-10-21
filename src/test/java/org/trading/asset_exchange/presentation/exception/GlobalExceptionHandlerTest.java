package org.trading.asset_exchange.presentation.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.trading.asset_exchange.BaseComponentTest;

class GlobalExceptionHandlerTest extends BaseComponentTest {


  @Test
  void handleUnsupportedVersionApi() throws Exception {
    MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/v00222/price?base=EUR&quote=USD")
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    int status = result.getResponse().getStatus();
    String content = result.getResponse().getContentAsString();

    // Assertions
    assertThat(status).isEqualTo(400);
    assertThat(content).isNotNull();
    assertThat(content.trim()).isNotEmpty();
  }

  @Test
  void handleInvalidParameter() throws Exception {
    MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/price?base1isInValid=EUR&quote=USD")
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    int status = result.getResponse().getStatus();
    String content = result.getResponse().getContentAsString();

    // Assertions
    assertThat(status).isEqualTo(400);
    assertThat(content).isNotNull();
    assertThat(content.trim()).isNotEmpty();
  }

  @Test
  void handleMethodArgumentNotValidException() throws Exception {
    MvcResult createdResult = mockMvc.perform(
            MockMvcRequestBuilders.post("/currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                    + "    \"quote\": \"USD\",\n"
                    + "    \"data_type\": \"chart\"\n"
                    + "}")
        )
        .andReturn();
    assertThat(createdResult.getResponse().getStatus()).isEqualTo(400);
  }
}