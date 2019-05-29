package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.NotificationDataEntity;
import com.papps.freddy_lazo.domain.model.NotificationData;

public class NotificationDataMapper {

    public static NotificationData transform(NotificationDataEntity data) {
        return new NotificationData(data.getType(), data.getAppointment_id(), data.getMessage());
    }
}
