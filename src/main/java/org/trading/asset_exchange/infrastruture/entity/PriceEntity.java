package org.trading.asset_exchange.infrastruture.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

/**
 base_currency  TEXT NOT NULL,
 quote_currency TEXT NOT NULL,
 close_time     TIMESTAMPTZ NOT NULL,
 average_bid    TEXT,
 average_ask    TEXT,
 high_bid       TEXT,
 high_ask       TEXT,
 low_bid        TEXT,
 low_ask        TEXT,
 updated_at     TIMESTAMPTZ NOT NULL DEFAULT NOW()
 */
@Data
@Entity
@Table(name = "price")
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @Column(name = "close_time")
  private String closeTime;

  @Column(name = "base_currency")
  private String baseCurrency;

  @Column(name = "quote_currency")
  private String quoteCurrency;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime timestamp;

}
