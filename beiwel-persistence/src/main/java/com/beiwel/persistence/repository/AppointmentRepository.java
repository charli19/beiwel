package com.beiwel.persistence.repository;

import com.beiwel.model.entity.AppointmentEntity;
import com.beiwel.model.filter.AppointmentFilter;
import com.beiwel.persistence.repository.specification.AppointmentSpecification;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends AbstractRepository<AppointmentEntity> {

  default Page<AppointmentEntity> findAllByFilter(AppointmentFilter appointmentFilter) {
    AppointmentSpecification appointmentSpecification = new AppointmentSpecification(
        appointmentFilter);

    Sort sort = null;
    if (appointmentFilter.getOrderReverse() != null && appointmentFilter.getOrderReverse()) {
      sort = Sort.by(Direction.ASC, "dateService", "startTime");
    } else {
      sort = Sort.by(Direction.DESC, "dateService", "startTime");
    }
    Pageable pageable = PageRequest.of(appointmentFilter.getPage(), appointmentFilter.getSize(),
        sort);

    return findAll(appointmentSpecification, pageable);
  }

  AppointmentEntity findById(long appointmentId);

  AppointmentEntity findByHash(String hash);

  List<AppointmentEntity> findAllByBusinessUserIdAndDateService(long id, LocalDate plusDays);

  List<AppointmentEntity> findAllByBusinessUserIdAndDateServiceAndStatusIdIn(long id,
      LocalDate plusDays, List<Long> statusConflictive);

  List<AppointmentEntity> findAllByStatusIdAndDateServiceLessThanEqual(long statusId,
      LocalDate today);

  @Query(value = "SELECT * FROM appointment a WHERE status_id = ?1 and date_service <= ?2 and start_time <= ?3",
          nativeQuery = true)
  List<AppointmentEntity> findAllByStatusIdAndDateServiceLessThanEqualNative(long statusId, String today, String time);

  List<AppointmentEntity> findAllByStatusIdAndBusinessUserIdAndDateServiceLessThanEqualOrderByStartTime(
      long statusId, long businessUserId, LocalDate today);

  List<AppointmentEntity> findAllByStatusIdAndDateServiceAndStartTimeBetween(long statusId,
      LocalDate today, LocalTime startTime, LocalTime endTime);

  List<AppointmentEntity> findAllByBusinessUserIdAndStatusIdInAndDateServiceBetween(
      long business_user_id, List<Long> statusId, LocalDate startDate, LocalDate finalDate);

}


