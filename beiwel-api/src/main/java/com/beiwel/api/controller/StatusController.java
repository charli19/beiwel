package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.StatusService;
import com.beiwel.model.entity.StatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("StatusController")
@RequestMapping(value = URLConstant.STATUS)
@PreAuthorize("isAuthenticated()")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping(value = "/{statusId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusEntity getStatusById(@PathVariable("statusId") long statusId) {
        return this.statusService.getStatusById(statusId);
    }

}