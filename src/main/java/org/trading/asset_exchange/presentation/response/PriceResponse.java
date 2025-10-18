package org.trading.asset_exchange.presentation.response;

import java.math.BigDecimal;

public record PriceResponse(String baseCurrency, String quoteCurrency, BigDecimal bidPrice, BigDecimal askPrice, String timestamp) {
}
