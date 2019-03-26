package com.papps.freddy_lazo.data.network;

import com.papps.freddy_lazo.data.entity.DoctorEntity;

import io.reactivex.Observable;

public interface RestApi {

    Observable<DoctorEntity> login(String email, String password);

}
