package com.beiwel.api.controller;


import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.UserService;
import com.beiwel.model.dto.UserDTO;
import com.beiwel.model.entity.UserEntity;
import com.beiwel.model.mapper.UserMapper;
import com.beiwel.model.view.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = URLConstant.PROFESSIONALS)
public class ProfessionalController {

    @Autowired
    public UserService userService;

    @GetMapping(value = "/{nickname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({UserView.Info.class})
    public UserEntity getUserInfoByNickname(
            @PathVariable("nickname") String nickname) {
        return userService.getUserInfoByUsername(nickname);
    }
}
