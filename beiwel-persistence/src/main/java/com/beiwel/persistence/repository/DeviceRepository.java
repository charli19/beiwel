package com.beiwel.persistence.repository;

import com.beiwel.model.entity.DeviceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends AbstractRepository<DeviceEntity> {

    List<DeviceEntity> findByUserId(Long userId);

    DeviceEntity findById(long deviceId);


}


