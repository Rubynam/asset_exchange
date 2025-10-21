package org.trading.asset_exchange;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.trading.asset_exchange.presentation.response.PriceResponse;
import org.trading.asset_exchange.presentation.scheduler.DataFetcher;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class BaseComponentTest {

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected DataFetcher dataFetcher;

  @BeforeEach
  void setup() throws Exception {
    dataFetcher.fetchData();
  }

  protected static class PricePageDto {
    public int number;
    public int size;
    public long totalElements;
    public List<PriceResponse> content;

  }
}
