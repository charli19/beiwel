package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.UserService;
import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.view.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = URLConstant.LOGIN)
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    @JsonView({UserView.Logged.class})
    public UserEntity getLoggedUser() {
        return userService.getLoggedUser();
    }

}
