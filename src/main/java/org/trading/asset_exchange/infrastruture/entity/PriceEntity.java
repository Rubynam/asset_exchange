package org.trading.asset_exchange.infrastruture.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "price")
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity implements Serializable
{

  @Id
  private Long id;

  @Column(name = "average_bid")
  private String averageBid;

  @Column(name = "average_ask")
  private String averageAsk;

  @Column(name = "high_bid")
  private String highBid;

  @Column(name = "high_ask")
  private String highAsk;

  @Column(name = "low_bid")
  private String lowBid;

  @Column(name = "low_ask")
  private String lowAsk;



}
