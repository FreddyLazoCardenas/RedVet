package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.NotificationData;
import com.papps.freddy_lazo.redvet.model.NotificationDataModel;

public class NotificationDataModelMapper {

    public NotificationDataModelMapper() {
    }

    public static NotificationDataModel transform(NotificationData data) {
        return new NotificationDataModel(data.getType(),data.getAppointmentId(),data.getMessage());
    }
}
