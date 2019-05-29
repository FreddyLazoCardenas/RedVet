package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.RedVetNotificationEntity;
import com.papps.freddy_lazo.domain.model.RedVetNotification;

import java.util.ArrayList;
import java.util.List;

public class RedVetNotificationMapper {

    public RedVetNotificationMapper() {
    }

    public static List<RedVetNotification> transform(List<RedVetNotificationEntity> data) {
        List<RedVetNotification> redVetNotifications = new ArrayList<>();
        if (data == null) {
            return redVetNotifications;
        }

        for (RedVetNotificationEntity redVetNotificationEntity : data) {
            redVetNotifications.add(transform(redVetNotificationEntity));
        }
        return redVetNotifications;
    }

    public static RedVetNotification transform(RedVetNotificationEntity data) {
        return new RedVetNotification(data.getId(), data.getUser_id(), data.getTitle(), data.getDescription(), NotificationDataMapper.transform(data.getData()), data.isRead());
    }
}
