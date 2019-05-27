package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.RedVetNotificationEntity;

public class RedVetNotificationsResponse {

    @SerializedName("notifications")
    private RedVetNotificationEntity notifications;

    public RedVetNotificationEntity getNotifications() {
        return notifications;
    }
}
