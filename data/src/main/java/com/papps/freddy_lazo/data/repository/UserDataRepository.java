package com.papps.freddy_lazo.data.repository;

import com.papps.freddy_lazo.data.entity.mapper.DoctorLoginMapper;
import com.papps.freddy_lazo.data.entity.mapper.SearchDoctorsMapper;
import com.papps.freddy_lazo.data.network.RestApi;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class UserDataRepository implements UserRepository {

    private final RestApi mRestApi;

    @Inject
    UserDataRepository(RestApi mRestApi) {
        this.mRestApi = mRestApi;
    }

    @Override
    public Observable<Doctor> login(String email, String password) {
        return mRestApi.login(email, password).map(DoctorLoginMapper::transform);
    }

    @Override
    public Observable<List<Doctor>> searchDoctors(ArrayList<String> type, ArrayList<Integer> services, ArrayList<Integer> pets, String text) {
        return mRestApi.searchDoctors(type, services, pets, text).map(SearchDoctorsMapper::transform);
    }

    @Override
    public Observable<Void> signUpPetLover(String email, String password, String firstName, String lastName, String dni, String address, String phone, String fcmToken, List<PetRegister> pets) {
        return mRestApi.signUpPetLover(email, password, firstName, lastName, dni, address, phone, fcmToken, pets);
    }

    @Override
    public Observable<Void> doctorRegister(String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services) {
        return mRestApi.doctorRegister(email, password, firstName, lastName, typeDocument, numberDocument, business_name, address, latitude, longitude, consultationTime, consultationPrice, shower_time, shower_price, tuition_number, description, phone, photo, type, attention, fcmToken, device, pets, schedules, services);
    }
}
