package org.trading.asset_exchange.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.trading.asset_exchange.presentation.response.PriceResponse;

public final class JsonUtil {
  private static final Gson GSON = new Gson();
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private JsonUtil() {

  }

  public static Map<String, Object> jsonStringToMap(String json) {
    if (StringUtils.isBlank(json)) {
      return java.util.Collections.emptyMap();
    }

    Type type = new TypeReference<Map<String, Object>>() {

    }.getType();
    return GSON.fromJson(json, type);
  }

  public static String mapToJsonString(Map<String, ?> map) {
    return GSON.toJson(map);
  }

}
