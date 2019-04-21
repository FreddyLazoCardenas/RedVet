package com.papps.freddy_lazo.redvet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceDoctorModel implements Parcelable {

    private Integer id;
    private int service_id;

    public ServiceDoctorModel(Integer id, int service_id) {
        this.id = id;
        this.service_id = service_id;
    }

    public Integer getId() {
        return id;
    }

    public int getService_id() {
        return service_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeInt(this.service_id);
    }

    protected ServiceDoctorModel(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.service_id = in.readInt();
    }

    public static final Parcelable.Creator<ServiceDoctorModel> CREATOR = new Parcelable.Creator<ServiceDoctorModel>() {
        @Override
        public ServiceDoctorModel createFromParcel(Parcel source) {
            return new ServiceDoctorModel(source);
        }

        @Override
        public ServiceDoctorModel[] newArray(int size) {
            return new ServiceDoctorModel[size];
        }
    };
}
