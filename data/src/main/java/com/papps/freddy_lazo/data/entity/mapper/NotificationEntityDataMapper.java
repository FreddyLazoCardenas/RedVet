package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.NotificationEntity;
import com.papps.freddy_lazo.domain.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationEntityDataMapper {

    public NotificationEntityDataMapper() {
    }

    public static Notification transform(NotificationEntity entity){
        if(entity == null) return null;
        return new Notification(entity.getType(),entity.getAppointment_id(),entity.getMessage(),entity.getTime(),entity.isFrom_doctor());
    }

    public static List<Notification> transformList(List<NotificationEntity> entities){
        List<Notification> stores = new ArrayList<>();
        for(NotificationEntity entity : entities){
            stores.add(transform(entity));
        }
        return stores;
    }

    public static NotificationEntity transform(Notification notification){
        if(notification == null) return null;
        return new NotificationEntity(notification.getType(),notification.getAppointment_id(),notification.getMessage(),notification.getTime(), notification.isFrom_doctor());
    }

    public static List<NotificationEntity> transform(List<Notification> notifications){
        List<NotificationEntity> entities = new ArrayList<>();
        for (Notification notification : notifications){
            entities.add(transform(notification));
        }
        return entities;
    }
}
