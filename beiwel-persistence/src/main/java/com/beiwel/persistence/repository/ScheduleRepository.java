package com.beiwel.persistence.repository;

import com.beiwel.model.entity.ScheduleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository
        extends AbstractRepository<ScheduleEntity> {

    List<ScheduleEntity> findAllByUserId(long userId);

    ScheduleEntity findById(long scheduleId);

    List<ScheduleEntity> findAllByUserIdAndDay(long userId, long dayOfWeek);
}
