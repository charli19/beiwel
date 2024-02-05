package com.beiwel.persistence.repository;

import com.beiwel.model.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractRepository<E extends AbstractEntity>
        extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {

}
