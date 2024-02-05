package com.beiwel.persistence.repository;

import com.beiwel.model.entity.LocationEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends AbstractRepository<LocationEntity> {

    LocationEntity findById(long locationId);

}
