
package com.beiwel.firebase.service.impl;

import com.beiwel.firebase.common.FirebaseConstant;
import com.beiwel.firebase.service.FirebaseMessagingService;
import com.beiwel.model.entity.DeviceEntity;
import com.beiwel.persistence.repository.DeviceRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FirebaseMessagingServiceImpl implements FirebaseMessagingService {

  @Autowired
  private FirebaseMessaging firebaseMessaging;

  @Autowired
  private DeviceRepository deviceRepository;

  public String sendNotification(String token, String body, Map<String, String> data) throws FirebaseMessagingException {

    Notification notification = Notification
        .builder()
        .setTitle(FirebaseConstant.NAME_BEIWEL_TITLE_APP)
        .setBody(body)
        .build();

    Message message = Message
        .builder()
        .setToken(token)
        .putAllData(data)
        .setNotification(notification)
        .build();

    return firebaseMessaging.send(message);
  }

  @Override
  @Transactional
  public void sendNotificationToDevices(long userId, String body, Map<String, String> data) {

    List<DeviceEntity> deviceEntityList = deviceRepository.findByUserId(userId);

    if (deviceEntityList.size() > 0) {
      DeviceEntity deviceEntity = deviceEntityList.get(0);

      try {
        this.sendNotification(deviceEntity.getTokenDevice(), body, data);
      } catch (Exception exception) {
        exception.printStackTrace();
        this.deviceRepository.delete(deviceEntity);
        return;
      }
    }
  }

  @Override
  @Transactional
  public void createDevice(DeviceEntity deviceEntity) {

    List<DeviceEntity> deviceEntityExistingList = deviceRepository.findByUserId(
        deviceEntity.getUser().getId());

    if (deviceEntityExistingList.size() > 0) {
      DeviceEntity deviceEntityExisting = deviceEntityExistingList.get(0);

      deviceEntityExisting.setTokenDevice(deviceEntity.getTokenDevice());
      this.deviceRepository.delete(deviceEntityExisting);
    }
    this.deviceRepository.save(deviceEntity);

  }
}

