package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.CategoryService;
import com.beiwel.business.service.CategoryUserService;
import com.beiwel.business.service.UserService;
import com.beiwel.model.entity.CategoryUserEntity;
import com.beiwel.model.filter.CategoryUserFilter;
import com.beiwel.model.pagination.CategoryUserPagination;
import com.beiwel.model.view.CategoryUserView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController("CategoryUserController")
@RequestMapping(value = URLConstant.CATEGORY_USERS)
@PreAuthorize("isAuthenticated()")
public class CategoryUserController {

    @Autowired
    private CategoryUserService categoryUserService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{categoryUserId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({CategoryUserView.Summary.class})
    public CategoryUserEntity getCategoryUserById(
            @PathVariable("categoryUserId") long categoryUserId) {
        return this.categoryUserService.getCategoryUserById(categoryUserId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({CategoryUserView.Summary.class})
    public CategoryUserPagination getCategoryUserList(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        CategoryUserFilter categoryUserFilter = categoryUserService.mapCategoryUserFilter(page, size,
                userId, categoryId);

        return this.categoryUserService.getCategoryUserList(categoryUserFilter);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({CategoryUserView.Create.class})
    public CategoryUserEntity createCategoryUser(Principal principal,
                                                 @RequestBody CategoryUserEntity categoryUserEntity) {

        return this.categoryUserService.createCategoryUser(categoryUserEntity);
    }

    @DeleteMapping(value = "/{categoryUserId}")
    public void deleteCategoryUser(@PathVariable("categoryUserId") long categoryUserId) {
        this.categoryUserService.deleteCategoryUser(categoryUserId);
    }

    @PutMapping(value = "/{categoryUserId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({CategoryUserView.Summary.class})
    public boolean editCategoryUser(@RequestBody CategoryUserEntity categoryUserEntity) {
        return this.categoryUserService.editCategoryUser(categoryUserEntity);
    }

}
