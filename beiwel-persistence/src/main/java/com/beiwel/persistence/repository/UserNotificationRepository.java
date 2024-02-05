package com.beiwel.persistence.repository;

import com.beiwel.model.entity.UserNotificationEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationRepository extends AbstractRepository<UserNotificationEntity> {

    List<UserNotificationEntity> getUserNotificationListByUserId(Long userId);
    List<UserNotificationEntity> getUserNotificationListByUserIdAndChannelId(Long userId, Long channelId);
    UserNotificationEntity getUserNotificationListByUserIdAndChannelIdAndTypeId(Long userId, Long channelId, Long typeId);
    UserNotificationEntity findById(long userId);

}
