package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.UserService;
import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.filter.UserFilter;
import com.beiwel.model.pagination.UserPagination;
import com.beiwel.model.view.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping(value = URLConstant.USERS)
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({UserView.Summary.class})
    public UserPagination getUsers(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "sessionAvailable", required = false) Boolean sessionAvailable,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        UserFilter userFilter = userService.mapUserFilter(page, size, userId, username, email, nickname, categoryId, sessionAvailable);
        return userService.getUsers(userFilter);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({UserView.Summary.class})
    public UserEntity getUserById(
            @PathVariable("userId") long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({UserView.Summary.class})
    public UserEntity getUserByUsername(
            @PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean editUser(@RequestBody @Valid UserEntity userEntity) {
        return this.userService.editUser(userEntity);
    }

    @PutMapping(value = "/preregister/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean preregisterUser(@RequestBody UserEntity userEntity) throws MessagingException {
        return this.userService.completePreregisterUser(userEntity);
    }

    @PutMapping(value = "/password/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean changePasswordUser(@RequestBody UserEntity userEntity) {
        return this.userService.changePasswordUser(userEntity);
    }

    @GetMapping(value = "/hash/{hashPreregister}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({UserView.Summary.class})
    public UserEntity getUserByHashPreregister(
            @PathVariable("hashPreregister") String hashPreregister) {
        return userService.getUserByHashPreregister(hashPreregister);
    }


}