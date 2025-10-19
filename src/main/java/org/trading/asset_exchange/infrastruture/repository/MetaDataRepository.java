package org.trading.asset_exchange.infrastruture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trading.asset_exchange.infrastruture.entity.MetaDataEntity;

@Repository
public interface MetaDataRepository extends JpaRepository<MetaDataEntity, Long> {


}
