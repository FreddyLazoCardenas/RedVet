package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyLogin {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("fcm_token")
    private String fcm_token;
    @SerializedName("device")
    private String device;

    public BodyLogin(String email, String password, String fcm_token, String device) {
        this.email = email;
        this.password = password;
        this.fcm_token = fcm_token;
        this.device = device;
    }
}
