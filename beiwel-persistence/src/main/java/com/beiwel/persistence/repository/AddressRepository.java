package com.beiwel.persistence.repository;

import com.beiwel.model.entity.AddressEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends AbstractRepository<AddressEntity> {

    AddressEntity findById(long addressId);

    List<AddressEntity> findByUserId(long userId);

    AddressEntity findByUserIdAndEnabled(long userId, boolean enable);


}
