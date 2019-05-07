package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.Notification;
import com.papps.freddy_lazo.redvet.model.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationModelMapper {

    public NotificationModelMapper() {
    }

    public static List<NotificationModel> transform(List<Notification> list) {
        List<NotificationModel> notificationModels = new ArrayList<>();
        if (list == null)
            return notificationModels;
        for (Notification notification : list) {
            notificationModels.add(transform(notification));
        }
        return notificationModels;
    }

    private static NotificationModel transform(Notification notification) {
        return new NotificationModel(notification.getId(), notification.getType(), notification.getAppointment_id(), notification.getMessage(), notification.getTime(), notification.isFrom_doctor());
    }
}
