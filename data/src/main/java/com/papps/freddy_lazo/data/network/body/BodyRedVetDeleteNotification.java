package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyRedVetDeleteNotification {

    @SerializedName("notification_id")
    private int notId;


    public BodyRedVetDeleteNotification(int notId) {
        this.notId = notId;
    }
}
