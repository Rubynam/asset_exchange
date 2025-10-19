package org.trading.asset_exchange.infrastruture.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "price")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaDataEntity implements Serializable {

  @Id
  private Long id;

  @Column(name = "metadata")
  private String metadata;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

}
