package com.beiwel.persistence.repository.specification;

import com.beiwel.model.entity.AbstractEntity;
import com.beiwel.model.filter.PaginationFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public abstract class AbstractSpecification<
    T extends AbstractEntity, F extends PaginationFilter>
    implements Specification<T> {

  @Getter
  private final F filter;

}