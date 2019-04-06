package com.papps.freddy_lazo.redvet.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.papps.freddy_lazo.domain.model.Schedule;
import com.papps.freddy_lazo.domain.model.ServiceDoctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorModel implements Parcelable {

    private int user_id;
    private String type_document;
    private String number_document;
    private String business_name;
    private String address;
    private String latitude;
    private String longitude;
    private String consultation_price;
    private String consultation_time;
    private String shower_price;
    private String shower_time;
    private String phone;
    private String type;
    private String tuition_number;
    private String description;
    private String attention;
    private String available;
    private String first_name;
    private String last_name;
    private String email;
    private String photo_url;
    private String api_token;
    private List<PetLoverRegisterModel> petList;
    private List<ScheduleModel> scheduleList;
    private List<ServiceDoctorModel> serviceList;


    public DoctorModel(int user_id, String type_document, String number_document, String business_name, String address, String latitude, String longitude, String consultation_price, String consultation_time,String shower_price ,String shower_time,String phone, String type, String tuition_number, String description, String attention
            , String available, String first_name, String last_name, String email, String photo_url, String api_token, List<PetLoverRegisterModel> petList,List<ScheduleModel> scheduleList,List<ServiceDoctorModel> serviceList) {
        this.user_id = user_id;
        this.type_document = type_document;
        this.number_document = number_document;
        this.business_name = business_name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.consultation_price = consultation_price;
        this.consultation_time = consultation_time;
        this.shower_price = shower_price;
        this.shower_time = shower_time;
        this.phone = phone;
        this.type = type;
        this.tuition_number = tuition_number;
        this.description = description;
        this.attention = attention;
        this.available = available;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.photo_url = photo_url;
        this.api_token = api_token;
        this.petList = petList;
        this.scheduleList = scheduleList;
        this.serviceList = serviceList;
    }

    public List<PetLoverRegisterModel> getPetList() {
        return petList;
    }

    public void setPetList(List<PetLoverRegisterModel> petList) {
        this.petList = petList;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getType_document() {
        return type_document;
    }

    public void setType_document(String type_document) {
        this.type_document = type_document;
    }

    public String getNumber_document() {
        return number_document;
    }

    public void setNumber_document(String number_document) {
        this.number_document = number_document;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getConsultation_price() {
        return consultation_price;
    }

    public void setConsultation_price(String consultation_price) {
        this.consultation_price = consultation_price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTuition_number() {
        return tuition_number;
    }

    public void setTuition_number(String tuition_number) {
        this.tuition_number = tuition_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public List<ScheduleModel> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<ScheduleModel> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public List<ServiceDoctorModel> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceDoctorModel> serviceList) {
        this.serviceList = serviceList;
    }

    public String getConsultation_time() {
        return consultation_time;
    }

    public void setConsultation_time(String consultation_time) {
        this.consultation_time = consultation_time;
    }

    public String getShower_price() {
        return shower_price;
    }

    public void setShower_price(String shower_price) {
        this.shower_price = shower_price;
    }

    public String getShower_time() {
        return shower_time;
    }

    public void setShower_time(String shower_time) {
        this.shower_time = shower_time;
    }

    public static DoctorModel toModel(String srtUser) {
        return srtUser != null && !srtUser.isEmpty() ? new Gson().fromJson(srtUser, DoctorModel.class) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.user_id);
        dest.writeString(this.type_document);
        dest.writeString(this.number_document);
        dest.writeString(this.business_name);
        dest.writeString(this.address);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeString(this.consultation_price);
        dest.writeString(this.consultation_time);
        dest.writeString(this.shower_price);
        dest.writeString(this.shower_time);
        dest.writeString(this.phone);
        dest.writeString(this.type);
        dest.writeString(this.tuition_number);
        dest.writeString(this.description);
        dest.writeString(this.attention);
        dest.writeString(this.available);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.email);
        dest.writeString(this.photo_url);
        dest.writeString(this.api_token);
        dest.writeList(this.petList);
        dest.writeList(this.scheduleList);
        dest.writeList(this.serviceList);
    }

    protected DoctorModel(Parcel in) {
        this.user_id = in.readInt();
        this.type_document = in.readString();
        this.number_document = in.readString();
        this.business_name = in.readString();
        this.address = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.consultation_price = in.readString();
        this.consultation_time = in.readString();
        this.shower_price = in.readString();
        this.shower_time = in.readString();
        this.phone = in.readString();
        this.type = in.readString();
        this.tuition_number = in.readString();
        this.description = in.readString();
        this.attention = in.readString();
        this.available = in.readString();
        this.first_name = in.readString();
        this.last_name = in.readString();
        this.email = in.readString();
        this.photo_url = in.readString();
        this.api_token = in.readString();
        this.petList = new ArrayList<>();
        in.readList(this.petList, PetModel.class.getClassLoader());
        this.scheduleList = new ArrayList<>();
        in.readList(this.scheduleList, PetModel.class.getClassLoader());
        this.serviceList = new ArrayList<>();
        in.readList(this.serviceList, PetModel.class.getClassLoader());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, DoctorModel.class);
    }

    public static final Parcelable.Creator<DoctorModel> CREATOR = new Parcelable.Creator<DoctorModel>() {
        @Override
        public DoctorModel createFromParcel(Parcel source) {
            return new DoctorModel(source);
        }

        @Override
        public DoctorModel[] newArray(int size) {
            return new DoctorModel[size];
        }
    };
}
