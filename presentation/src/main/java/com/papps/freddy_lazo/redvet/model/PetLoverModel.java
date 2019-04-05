package com.papps.freddy_lazo.redvet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.papps.freddy_lazo.domain.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetLoverModel implements Parcelable {

    private int user_id;
    private String dni;
    private String address;
    private String phone;
    private String first_name;
    private String last_name;
    private String photo_url;
    private String email;
    private String api_token;
    private List<PetLoverRegisterModel> petList;

    public PetLoverModel(int user_id, String dni, String address, String phone, String first_name, String last_name, String photo_url, String email, String api_token, List<PetLoverRegisterModel> petList) {
        this.user_id = user_id;
        this.dni = dni;
        this.address = address;
        this.phone = phone;
        this.first_name = first_name;
        this.last_name = last_name;
        this.photo_url = photo_url;
        this.email = email;
        this.api_token = api_token;
        this.petList = petList;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public List<PetLoverRegisterModel> getPetList() {
        return petList;
    }

    public void setPetList(List<PetLoverRegisterModel> petList) {
        this.petList = petList;
    }

    public static PetLoverModel toModel(String srtUser) {
        return srtUser != null && !srtUser.isEmpty() ? new Gson().fromJson(srtUser, PetLoverModel.class) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.user_id);
        dest.writeString(this.dni);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.photo_url);
        dest.writeString(this.email);
        dest.writeString(this.api_token);
        dest.writeList(this.petList);
    }

    protected PetLoverModel(Parcel in) {
        this.user_id = in.readInt();
        this.dni = in.readString();
        this.address = in.readString();
        this.phone = in.readString();
        this.first_name = in.readString();
        this.last_name = in.readString();
        this.photo_url = in.readString();
        this.email = in.readString();
        this.api_token = in.readString();
        this.petList = new ArrayList<PetLoverRegisterModel>();
        in.readList(this.petList, PetModel.class.getClassLoader());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, PetLoverModel.class);
    }

    public static final Parcelable.Creator<PetLoverModel> CREATOR = new Parcelable.Creator<PetLoverModel>() {
        @Override
        public PetLoverModel createFromParcel(Parcel source) {
            return new PetLoverModel(source);
        }

        @Override
        public PetLoverModel[] newArray(int size) {
            return new PetLoverModel[size];
        }
    };
}
