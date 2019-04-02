package com.papps.freddy_lazo.domain.model;


public class News {

    private int id;
    private String title;
    private String content;
    private String photo;
    private String photo_url;
    private String type;

    public News(int id, String title, String content, String photo, String photo_url, String type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.photo = photo;
        this.photo_url = photo_url;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
