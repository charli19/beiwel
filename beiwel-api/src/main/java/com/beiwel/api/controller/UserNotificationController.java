package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.UserNotificationService;
import com.beiwel.model.entity.UserNotificationEntity;
import com.beiwel.model.view.UserNotificationView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = URLConstant.USER_NOTIFICATION)
@PreAuthorize("isAuthenticated()")
public class UserNotificationController {
    @Autowired
    public UserNotificationService userNotificationService;

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({UserNotificationView.Summary.class})
    public List<UserNotificationEntity> getUserNotificationByUserId(@PathVariable("userId") Long userId) {
        return this.userNotificationService.getUserNotificationListByUserId(userId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView({UserNotificationView.Summary.class})
    public List<UserNotificationEntity> getUserNotificationByUserIdAndChannelId(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "channelId") Long channelId
    ) {
        return this.userNotificationService.getUserNotificationByUserIdAndChannelId(userId, channelId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean editUserNotification(@RequestBody @Valid UserNotificationEntity userNotificationEntity) {
        return this.userNotificationService.editUserNotification(userNotificationEntity);
    }



}