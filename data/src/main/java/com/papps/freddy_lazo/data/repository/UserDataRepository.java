package com.papps.freddy_lazo.data.repository;

import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.data.entity.mapper.DoctorLoginMapper;
import com.papps.freddy_lazo.data.network.RestApi;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

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
}
