package org.trading.asset_exchange.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SymbolRequest {

  private String base;
  private String quote;
  @JsonProperty("data_type")
  private String dataType;

  public Map<String, ?> toMap() {
    return Map.of("base", base, "quote", quote, "chart", dataType);
  }
}
