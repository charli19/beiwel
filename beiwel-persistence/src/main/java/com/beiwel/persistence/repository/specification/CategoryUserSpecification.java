package com.beiwel.persistence.repository.specification;

import com.beiwel.model.entity.CategoryEntity;
import com.beiwel.model.entity.CategoryUserEntity;
import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.filter.CategoryUserFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.lang.NonNull;

public class CategoryUserSpecification extends AbstractSpecification<CategoryUserEntity, CategoryUserFilter> {

  public CategoryUserSpecification(CategoryUserFilter filter) {
    super(filter);
  }

  @Override
  public Predicate toPredicate(
      @NonNull Root<CategoryUserEntity> root,
      @NonNull CriteriaQuery<?> query,
      @NonNull CriteriaBuilder criteriaBuilder) {

    CategoryUserFilter filter = this.getFilter();
    List<Predicate> restrictions = new ArrayList<>();

    if (filter.getUserId() != null) {
      Join<UserEntity, String> join = root.join("user");
      restrictions.add(
          criteriaBuilder.equal(join.get("id"), filter.getUserId()));
    }


    if (filter.getCategoryId() != null) {
      Join<CategoryEntity, String> join = root.join("category");
      restrictions.add(
              criteriaBuilder.equal(join.get("id"), filter.getCategoryId()));
    }

    return criteriaBuilder.and(restrictions.toArray(new Predicate[0]));
  }

}
