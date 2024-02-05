package com.beiwel.persistence.repository.specification;

import com.beiwel.model.entity.CategoryUserEntity;
import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.filter.UserFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

public class UserSpecification extends AbstractSpecification<UserEntity, UserFilter> {

  public UserSpecification(UserFilter filter) {
    super(filter);
  }

  @Override
  public Predicate toPredicate(
      @NonNull Root<UserEntity> root,
      @NonNull CriteriaQuery<?> query,
      @NonNull CriteriaBuilder criteriaBuilder) {

    UserFilter filter = this.getFilter();
    List<Predicate> restrictions = new ArrayList<>();

    if (filter.getUserId() != null) {
      restrictions.add(criteriaBuilder.equal(root.get("id"), filter.getUserId()));
    }

    if (!StringUtils.isEmpty(filter.getUsername())) {
      restrictions.add(criteriaBuilder.equal(root.get("username"), filter.getUsername()));
    }

    if (!StringUtils.isEmpty(filter.getEmail())) {
      restrictions.add(criteriaBuilder.equal(root.get("email"), filter.getEmail()));
    }

    if (!StringUtils.isEmpty(filter.getNickname())) {
      restrictions.add(criteriaBuilder.equal(root.get("nickname"), filter.getNickname()));
    }

    if (filter.getSessionAvailable() != null && filter.getSessionAvailable()) {

      restrictions.add(criteriaBuilder.isNotEmpty(root.get("sessions")));
    }

    if (filter.getCategoryId() != null) {
      Join<CategoryUserEntity, Long> join = root.join("categoryUser").join("category");
      restrictions.add(
          criteriaBuilder.equal(join.get("id"), filter.getCategoryId()));
    }

    return criteriaBuilder.and(restrictions.toArray(new Predicate[0]));
  }

}
