package com.papps.freddy_lazo.redvet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsModel implements Parcelable {

    private int id;
    private String title;
    private String content;
    private String photo;
    private String photo_url;
    private String type;

    public NewsModel(int id, String title, String content, String photo, String photo_url, String type) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.photo);
        dest.writeString(this.photo_url);
        dest.writeString(this.type);
    }

    protected NewsModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.content = in.readString();
        this.photo = in.readString();
        this.photo_url = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<NewsModel> CREATOR = new Parcelable.Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel source) {
            return new NewsModel(source);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };
}
