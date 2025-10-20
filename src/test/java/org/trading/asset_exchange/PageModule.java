package org.trading.asset_exchange;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageModule extends SimpleModule {

  public PageModule() {
    super("PageModule");
    addDeserializer(PageImpl.class, new PageStringDeserializer());
  }

  private static class PageStringDeserializer extends JsonDeserializer<PageImpl<String>> {
    @Override
    public PageImpl<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<String> content = new ArrayList<>();


      long totalElements = node.get("totalElements").asLong();
      int size = node.get("size").asInt();
      int number = node.get("number").asInt();
      node.get("content").elements()
          .forEachRemaining(item -> content.add(item.asText()));

      Pageable pageable = PageRequest.of(number, size);
      return new PageImpl<>(content, pageable, totalElements);
    }
  }
}
