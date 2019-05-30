package com.papps.freddy_lazo.redvet.model;


public class RedVetNotificationModel {

    private int id;
    private int userId;
    private String title;
    private NotificationDataModel data;
    private String description;
    private String time;
    private boolean isRead;

    public RedVetNotificationModel(int id, int userId, String title, NotificationDataModel data, String description, boolean isRead, String time) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.data = data;
        this.description = description;
        this.time = time;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public NotificationDataModel getData() {
        return data;
    }

    public boolean isRead() {
        return isRead;
    }

    public String getTime() {
        return time;
    }
}
