package org.trading.asset_exchange.presentation.controller;


import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping
  public ResponseEntity<MetadataResponse> createMetaData(@RequestBody Map<String,?> metadata)
      throws Exception {
    return new ResponseEntity<>(metaDataCommand.execute(metadata), HttpStatus.ACCEPTED);
  }
}
