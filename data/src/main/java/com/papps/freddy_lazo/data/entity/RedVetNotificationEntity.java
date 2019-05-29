package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RedVetNotificationEntity {

    @SerializedName("id")
    private int id;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("title")
    private int title;
    @SerializedName("description")
    private int description;
    @SerializedName("type")
    private String type;
}
