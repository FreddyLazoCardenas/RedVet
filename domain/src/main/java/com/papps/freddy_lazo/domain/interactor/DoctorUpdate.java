package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DoctorUpdate extends UseCase {

    private final UserRepository repository;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String typeDocument;
    private String numberDocument;
    private String business_name;
    private String address;
    private String latitude;
    private String longitude;
    private String consultationPrice;
    private String consultationTime;
    private String shower_price;
    private String shower_time;
    private String tuition_number;
    private String description;
    private String photo;
    private String attention;
    private String type;
    private String device;
    private List<ScheduleDoctorRegister> schedules;
    private List<ServicesDoctorRegister> services;
    private String phone;
    private String fcmToken;
    private String apiToken;
    private List<PetRegister> pets;

    @Inject
    DoctorUpdate(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String apiToken ,String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services) {
        this.apiToken = apiToken;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeDocument = typeDocument;
        this.numberDocument = numberDocument;
        this.business_name = business_name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.consultationPrice = consultationPrice;
        this.consultationTime = consultationTime;
        this.shower_price = shower_price;
        this.shower_time = shower_time;
        this.tuition_number = tuition_number;
        this.description = description;
        this.photo = photo;
        this.type = type;
        this.attention = attention;
        this.device = device;
        this.schedules = schedules;
        this.services = services;
        this.phone = phone;
        this.fcmToken = fcmToken;
        this.pets = pets;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.updateDoctor(apiToken,email, password, firstName, lastName, typeDocument, numberDocument, business_name, address, latitude, longitude, consultationPrice, consultationTime, shower_price, shower_time, tuition_number, description, phone, photo, type, attention
                , fcmToken, device, pets, schedules, services);
    }
}
