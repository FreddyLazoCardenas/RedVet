package com.papps.freddy_lazo.redvet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ServicesModel implements Parcelable {

    private int id;
    private String name;
    private String available;
    private boolean state;
    private Integer responseId;

    public ServicesModel(int id, String name, String available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    protected ServicesModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        available = in.readString();
        state = in.readByte() != 0;
        if (in.readByte() == 0) {
            responseId = null;
        } else {
            responseId = in.readInt();
        }
    }

    public static final Creator<ServicesModel> CREATOR = new Creator<ServicesModel>() {
        @Override
        public ServicesModel createFromParcel(Parcel in) {
            return new ServicesModel(in);
        }

        @Override
        public ServicesModel[] newArray(int size) {
            return new ServicesModel[size];
        }
    };

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

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(available);
        dest.writeByte((byte) (state ? 1 : 0));
        if (responseId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(responseId);
        }
    }
}
