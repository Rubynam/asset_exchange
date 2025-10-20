package org.trading.asset_exchange.presentation.controller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trading.asset_exchange.application.command.MetaDataCommand;
import org.trading.asset_exchange.application.query.MetadataQuery;
import org.trading.asset_exchange.presentation.response.MetadataResponse;

@RestController
@RequestMapping("/metadata")
@RequiredArgsConstructor
public class MetaDataController {

  private final MetadataQuery metadataQuery;
  private final MetaDataCommand metaDataCommand;

  @GetMapping
  public List<MetadataResponse> getMetaData() {
    return metadataQuery.getMetaData();
  }

}
