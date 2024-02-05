package com.beiwel.persistence.repository.specification;

import com.beiwel.model.entity.AppointmentEntity;
import com.beiwel.model.entity.StatusEntity;
import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.filter.AppointmentFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.lang.NonNull;

public class AppointmentSpecification extends AbstractSpecification<AppointmentEntity, AppointmentFilter> {

    public AppointmentSpecification(AppointmentFilter filter) {
        super(filter);
    }

    @Override
    public Predicate toPredicate(
            @NonNull Root<AppointmentEntity> root,
            @NonNull CriteriaQuery<?> query,
            @NonNull CriteriaBuilder criteriaBuilder) {

        AppointmentFilter filter = this.getFilter();
        List<Predicate> restrictions = new ArrayList<>();


        if (filter.getSessionId() != null) {
            Join<StatusEntity, Long> join = root.join("session");
            restrictions.add(
                    criteriaBuilder.equal(join.get("id"), filter.getSessionId()));
        }

        if (filter.getStatusIds() != null) {
            Join<StatusEntity, Long> join = root.join("status");
            restrictions.add(
                    join.get("id").in(filter.getStatusIds()));
        }

        if (filter.getBusinessUserId() != null) {
            Join<UserEntity, Long> join = root.join("businessUser");
            restrictions.add(
                    criteriaBuilder.equal(join.get("id"), filter.getBusinessUserId()));
        }

        if (filter.getClientUserId() != null) {
            Join<UserEntity, Long> join = root.join("clientUser");
            restrictions.add(
                    criteriaBuilder.equal(join.get("id"), filter.getClientUserId()));
        }

        if (filter.getManual() != null) {
            restrictions.add(criteriaBuilder.equal(root.get("manual"), filter.getManual()));
        }


        return criteriaBuilder.and(restrictions.toArray(new Predicate[0]));
    }

}
