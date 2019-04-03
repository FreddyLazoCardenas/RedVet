package com.papps.freddy_lazo.data.repository;

import com.papps.freddy_lazo.data.entity.mapper.DoctorLoginMapper;
import com.papps.freddy_lazo.data.entity.mapper.SearchDoctorsMapper;
import com.papps.freddy_lazo.data.network.RestApi;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.domain.model.PetRegister;
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
        return mRestApi.login(email,password).map(DoctorLoginMapper::transform);
    }

    @Override
    public Observable<List<Doctor>> searchDoctors(ArrayList<String> type, ArrayList<Integer> services, ArrayList<Integer> pets, String text) {
        return mRestApi.searchDoctors(type,services,pets,text).map(SearchDoctorsMapper::transform);
    }

    @Override
    public Observable<Void> signUpPetLover(String email, String password, String firstName, String lastName, String dni, String address, String phone, String fcmToken, List<PetRegister> pets) {
        return mRestApi.signUpPetLover(email,password,firstName,lastName,dni,address,phone,fcmToken,pets);
    }
}
