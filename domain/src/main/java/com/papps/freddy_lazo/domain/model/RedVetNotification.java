package com.papps.freddy_lazo.domain.model;

public class RedVetNotification {

    private int id;
    private int userId;
    private String title;
    private String description;
    private String time;
    private NotificationData data;
    private boolean isRead;

    public RedVetNotification(int id, int userId, String title, String description, NotificationData data, boolean isRead, String time) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.data = data;
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

    public NotificationData getData() {
        return data;
    }

    public boolean isRead() {
        return isRead;
    }

    public String getTime() {
        return time;
    }
}
