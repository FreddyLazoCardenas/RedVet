package com.papps.freddy_lazo.redvet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ServicesModel implements Parcelable {

    private int id;
    private String name;
    private String available;
    private boolean state;

    public ServicesModel(int id, String name, String available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.available);
        dest.writeByte(this.state ? (byte) 1 : (byte) 0);
    }

    protected ServicesModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.available = in.readString();
        this.state = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ServicesModel> CREATOR = new Parcelable.Creator<ServicesModel>() {
        @Override
        public ServicesModel createFromParcel(Parcel source) {
            return new ServicesModel(source);
        }

        @Override
        public ServicesModel[] newArray(int size) {
            return new ServicesModel[size];
        }
    };
}
