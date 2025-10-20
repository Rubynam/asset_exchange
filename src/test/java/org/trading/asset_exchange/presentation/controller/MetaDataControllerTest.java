package org.trading.asset_exchange.presentation.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.trading.asset_exchange.BaseComponentTest;
import org.trading.asset_exchange.infrastruture.entity.MetaDataEntity;
import org.trading.asset_exchange.infrastruture.repository.MetaDataRepository;

class MetaDataControllerTest extends BaseComponentTest {



  @MockitoSpyBean
  private MetaDataRepository metaDataRepository;

  @Test
  void shouldGetMetaDataAndReturnEmpty() throws Exception {
    MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/metadata")
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    int status = result.getResponse().getStatus();
    assertThat(status).isEqualTo(200);

    String content = result.getResponse().getContentAsString();
    assertThat(content).isNotNull();
  }

  @Test
  void shouldGetMetaDataAndThrowError() throws Exception {

    //when
    Mockito.doThrow(new RuntimeException("Mocked error")).when(metaDataRepository).findAll();

    MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/metadata")
                .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

    int status = result.getResponse().getStatus();
    assertThat(status).isEqualTo(500);

    String content = result.getResponse().getContentAsString();
    assertThat(content).isNotNull();
    assertTrue(content.contains("Mocked error"));
  }

  @Test
  void shouldGetMetaDataAndReturnNonEmpty() throws Exception {
    LocalDateTime now = LocalDateTime.now();
    MetaDataEntity metaDataEntity = MetaDataEntity.builder()
        .metadata("{\n"
            + "    \"base\": \"JPY\",\n"
            + "    \"quote\": \"USD\",\n"
            + "    \"data_type\": \"chart\"\n"
            + "}")
        .updatedAt(now)
        .build();
    metaDataRepository.save(metaDataEntity);

    try {
      MvcResult result = mockMvc.perform(
              MockMvcRequestBuilders.get("/metadata")
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

}