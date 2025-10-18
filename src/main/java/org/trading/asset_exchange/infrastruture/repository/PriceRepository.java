package org.trading.asset_exchange.infrastruture.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trading.asset_exchange.infrastruture.entity.PriceEntity;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

  Page<PriceEntity> findAllByBaseCurrencyAndQuoteCurrency(String baseCurrency, String quoteCurrency, Pageable pageable);
}
