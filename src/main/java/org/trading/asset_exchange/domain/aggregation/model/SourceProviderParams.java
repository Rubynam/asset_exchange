package org.trading.asset_exchange.domain.aggregation.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SourceProviderParams {

  private String name;
  private String url;
  private Map<String,?> params;

}
