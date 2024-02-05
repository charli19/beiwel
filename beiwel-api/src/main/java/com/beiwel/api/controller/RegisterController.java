package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.UserService;
import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.view.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("RegisterController")
@RequestMapping(value = URLConstant.REGISTER)
@PreAuthorize("isAuthenticated()")
public class RegisterController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/{roleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  @JsonView({UserView.Register.class})
  public UserEntity createUser(@RequestBody UserEntity userEntity,
      @PathVariable("roleId") long roleId) {
    return userService.createUser(userEntity, roleId);
  }
}
