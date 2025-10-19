package org.trading.asset_exchange.application.port;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.trading.asset_exchange.infrastruture.entity.PriceEntity;
import org.trading.asset_exchange.presentation.response.PriceResponse;
import org.trading.asset_exchange.util.DateTimeFormatterUtil;

@Service
public class PriceResponseTransformer implements Transformer<PriceEntity, PriceResponse> {

  @Override
  public PriceResponse transform(PriceEntity input) {
    BigDecimal bidPrice = BigDecimal.valueOf(Double.parseDouble(input.getAverageBid()));
    BigDecimal askPrice = BigDecimal.valueOf(Double.parseDouble(input.getAverageAsk()));
    String updatedAt = DateTimeFormatterUtil.getFormattedDate(input.getTimestamp(), DateTimeFormatterUtil.YYYY_MM_DD);
    return new PriceResponse(input.getBaseCurrency(), input.getQuoteCurrency(), bidPrice, askPrice, updatedAt);
  }

  @Override
  public PriceEntity reverseTransform(PriceResponse output) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
