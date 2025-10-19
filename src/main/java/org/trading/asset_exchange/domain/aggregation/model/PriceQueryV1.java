package org.trading.asset_exchange.domain.aggregation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceQueryV1 implements PriceQueryContract {

  private String baseCurrency;
  private String quoteCurrency;
  private int page;
  private int size;

}
