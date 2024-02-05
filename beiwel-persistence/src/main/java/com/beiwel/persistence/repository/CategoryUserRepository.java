package com.beiwel.persistence.repository;

import com.beiwel.model.entity.CategoryUserEntity;
import com.beiwel.model.filter.CategoryUserFilter;
import com.beiwel.persistence.repository.specification.CategoryUserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryUserRepository extends AbstractRepository<CategoryUserEntity> {

    CategoryUserEntity findById(long categoryUserId);

    default Page<CategoryUserEntity> findAllByFilter(CategoryUserFilter categoryUserFilter) {
        CategoryUserSpecification categoryUserSpecification = new CategoryUserSpecification(categoryUserFilter);

        Sort sort = Sort.by(Direction.DESC, "createdDate");
        Pageable pageable =
                PageRequest.of(categoryUserFilter.getPage(), categoryUserFilter.getSize(), sort);

        return findAll(categoryUserSpecification, pageable);
    }
}
