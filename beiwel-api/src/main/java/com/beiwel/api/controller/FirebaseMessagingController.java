
package com.beiwel.api.controller;

import com.beiwel.api.common.URLConstant;
import com.beiwel.business.service.DeviceService;
import com.beiwel.firebase.service.FirebaseMessagingService;
import com.beiwel.model.entity.DeviceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController("FirebaseMessagingController")
@RequestMapping(value = URLConstant.NOTIFICATIONS)
@PreAuthorize("isAuthenticated()")
public class FirebaseMessagingController {

    @Autowired
    private FirebaseMessagingService firebaseMessagingService;
    @Autowired
    private DeviceService deviceService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveDevice(@RequestBody DeviceEntity deviceEntity) {

        this.firebaseMessagingService.createDevice(deviceEntity);
    }


    @DeleteMapping(value = "/{userId}")
    public void deleteSchedule(
            @PathVariable("userId") long userId) {
        this.deviceService.deleteDeviceByUserId(userId);
    }


}
