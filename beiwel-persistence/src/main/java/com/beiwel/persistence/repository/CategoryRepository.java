package com.beiwel.persistence.repository;

import com.beiwel.model.entity.AppointmentEntity;
import com.beiwel.model.entity.CategoryEntity;
import com.beiwel.model.filter.CategoryFilter;
import com.beiwel.persistence.repository.specification.CategorySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends AbstractRepository<CategoryEntity> {

    default Page<CategoryEntity> findAllByFilter(CategoryFilter categoryFilter) {
        CategorySpecification categorySpecification = new CategorySpecification(categoryFilter);

        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable =
                PageRequest.of(categoryFilter.getPage(), categoryFilter.getSize(), sort);

        return findAll(categorySpecification, pageable);
    }

    CategoryEntity findByName(String name);

}
