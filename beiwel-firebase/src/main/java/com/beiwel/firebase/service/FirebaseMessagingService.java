
package com.beiwel.firebase.service;

import com.beiwel.model.entity.DeviceEntity;
import com.google.firebase.messaging.FirebaseMessagingException;

import java.util.Map;

public interface FirebaseMessagingService {

    String sendNotification(String token, String body, Map<String, String> data) throws FirebaseMessagingException;

    void createDevice(DeviceEntity deviceEntity);

    void sendNotificationToDevices(long userId, String body, Map<String, String> data);

}
