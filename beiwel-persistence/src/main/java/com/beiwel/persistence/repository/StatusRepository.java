package com.beiwel.persistence.repository;

import com.beiwel.model.entity.StatusEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends AbstractRepository<StatusEntity> {

    StatusEntity findById(long statusId);

    StatusEntity findByName(String name);

}
