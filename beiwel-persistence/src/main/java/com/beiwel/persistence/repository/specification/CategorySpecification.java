package com.beiwel.persistence.repository.specification;

import com.beiwel.model.entity.CategoryEntity;
import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.filter.CategoryFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.lang.NonNull;

public class CategorySpecification extends AbstractSpecification<CategoryEntity, CategoryFilter> {

  public CategorySpecification(CategoryFilter filter) {
    super(filter);
  }

  @Override
  public Predicate toPredicate(
      @NonNull Root<CategoryEntity> root,
      @NonNull CriteriaQuery<?> query,
      @NonNull CriteriaBuilder criteriaBuilder) {

    CategoryFilter filter = this.getFilter();
    List<Predicate> restrictions = new ArrayList<>();

    if (filter.getCategoryId() != null) {
      restrictions.add(criteriaBuilder.equal(root.get("id"), filter.getUserId()));
    }

    //No se usa creo, pero esta en controlador, y no esta testeado
    if (filter.getUserId() != null) {
      Join<UserEntity, Long> join = root.join("user");
      restrictions.add(
              criteriaBuilder.equal(join.get("id"), filter.getUserId()));
    }

    if (filter.getStandard() != null) {
      restrictions.add(criteriaBuilder.equal(root.get("standard"), filter.getStandard()));
    }

    return criteriaBuilder.and(restrictions.toArray(new Predicate[0]));
  }

}
