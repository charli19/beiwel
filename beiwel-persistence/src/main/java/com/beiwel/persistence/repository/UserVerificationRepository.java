package com.beiwel.persistence.repository;

import com.beiwel.model.entity.AppointmentEntity;
import com.beiwel.model.entity.UserVerificationEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVerificationRepository extends AbstractRepository<UserVerificationEntity> {

  UserVerificationEntity findByCode(String code);

  List<UserVerificationEntity> findAll();

  List<UserVerificationEntity> findAllByExpiration(boolean expiration);

}
