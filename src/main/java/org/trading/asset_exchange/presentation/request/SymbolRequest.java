package org.trading.asset_exchange.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
public class SymbolRequest {

  @NotBlank(message = "base is mandatory")
  private String base;
  @NotBlank(message = "quote is mandatory")
  private String quote;
  @JsonProperty("data_type")
  @NotBlank(message = "data_type is mandatory")
  private String dataType;

  public Map<String, ?> toMap() {
    return Map.of("base", base, "quote", quote, "chart", dataType);
  }
}
