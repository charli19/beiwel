package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.SessionTypeService;
import com.beiwel.model.entity.SessionTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("SessionTypeController")
@RequestMapping(value = URLConstant.SESSION_TYPE)
@PreAuthorize("isAuthenticated()")
public class SessionTypeController {
    @Autowired
    private SessionTypeService sessionTypeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SessionTypeEntity> getSessionTypeList() {
        return this.sessionTypeService.getSessionTypeList();
    }

}