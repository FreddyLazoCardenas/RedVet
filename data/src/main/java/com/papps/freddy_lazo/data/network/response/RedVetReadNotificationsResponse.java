package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.RedVetNotificationEntity;

import java.util.List;

public class RedVetReadNotificationsResponse {

    @SerializedName("notification")
    private RedVetNotificationEntity notification;

    public RedVetNotificationEntity getNotification() {
        return notification;
    }
}
