package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.RedVetNotificationEntity;

import java.util.List;

public class RedVetNotificationsResponse {

    @SerializedName("notifications")
    private List<RedVetNotificationEntity> notifications;

    public List<RedVetNotificationEntity> getNotifications() {
        return notifications;
    }
}
