package org.trading.asset_exchange.presentation.response;

import java.time.LocalDateTime;
import java.util.Map;

public record MetadataResponse(Map<String,?> metadata, LocalDateTime timestamp) {

}
