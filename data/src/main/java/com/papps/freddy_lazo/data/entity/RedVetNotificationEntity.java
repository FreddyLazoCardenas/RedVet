package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RedVetNotificationEntity {

    @SerializedName("id")
    private int id;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("data")
    private NotificationDataEntity data;
    @SerializedName("is_read")
    private boolean is_read;
    @SerializedName("created_at")
    private String time;

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public NotificationDataEntity getData() {
        return data;
    }

    public boolean isRead() {
        return is_read;
    }

    public String getTime() {
        return time;
    }
}
