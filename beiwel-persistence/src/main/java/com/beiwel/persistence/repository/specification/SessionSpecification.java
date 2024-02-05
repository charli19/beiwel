package com.beiwel.persistence.repository.specification;

import com.beiwel.model.entity.SessionEntity;
import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.filter.SessionFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.lang.NonNull;

public class SessionSpecification extends AbstractSpecification<SessionEntity, SessionFilter> {

    public SessionSpecification(SessionFilter filter) {
        super(filter);
    }

    @Override
    public Predicate toPredicate(
            @NonNull Root<SessionEntity> root,
            @NonNull CriteriaQuery<?> query,
            @NonNull CriteriaBuilder criteriaBuilder) {

        SessionFilter filter = this.getFilter();
        List<Predicate> restrictions = new ArrayList<>();

        if (filter.getUserId() != null) {
            Join<UserEntity, String> join = root.join("user");
            restrictions.add(
                    criteriaBuilder.equal(join.get("id"), filter.getUserId()));
        }

        if (filter.getEnabled() != null) {
            restrictions.add(criteriaBuilder.equal(root.get("enabled"), filter.getEnabled()));
        }

        return criteriaBuilder.and(restrictions.toArray(new Predicate[0]));
    }

}
