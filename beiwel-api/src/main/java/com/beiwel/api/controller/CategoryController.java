package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.CategoryService;
import com.beiwel.model.entity.CategoryEntity;
import com.beiwel.model.entity.ScheduleEntity;
import com.beiwel.model.filter.CategoryFilter;
import com.beiwel.model.pagination.CategoryPagination;
import com.beiwel.model.view.CategoryView;
import com.beiwel.model.view.ScheduleView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController("CategoryController")
@RequestMapping(value = URLConstant.CATEGORIES)
@PreAuthorize("isAuthenticated()")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({CategoryView.Summary.class})
    public CategoryPagination getCategoryList(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "standard", required = false) Boolean standard,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        CategoryFilter categoryFilter = categoryService.mapCategoryFilter(page, size, userId,
                categoryId, standard);

        return this.categoryService.getCategoryList(categoryFilter);
    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({CategoryView.Summary.class})
    public CategoryEntity createCategory(
            @RequestBody CategoryEntity categoryEntity) {

        return this.categoryService.createCategory(categoryEntity);
    }

}
