package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.RedVetNotification;
import com.papps.freddy_lazo.redvet.model.RedVetNotificationModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationModelMapper {

    public NotificationModelMapper() {
    }

    public static List<RedVetNotificationModel> transform(List<RedVetNotification> list) {
        List<RedVetNotificationModel> notificationModels = new ArrayList<>();
        if (list == null)
            return notificationModels;
        for (RedVetNotification notification : list) {
            notificationModels.add(transform(notification));
        }
        return notificationModels;
    }

    public static RedVetNotificationModel transform(RedVetNotification notification) {
        return new RedVetNotificationModel(notification.getId(), notification.getUserId(), notification.getTitle(), NotificationDataModelMapper.transform(notification.getData()), notification.getDescription(), notification.isRead(),notification.getTime());
    }
}
