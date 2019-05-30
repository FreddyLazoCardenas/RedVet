package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyRedVetReadNotification {

    @SerializedName("notification_id")
    private int notId;

    @SerializedName("is_read")
    private boolean isRead;

    public BodyRedVetReadNotification(int notId, boolean isRead) {
        this.notId = notId;
        this.isRead = isRead;
    }
}
