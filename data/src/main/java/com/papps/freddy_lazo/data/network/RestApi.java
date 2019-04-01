package com.papps.freddy_lazo.data.network;

import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.data.entity.NewsEntity;
import com.papps.freddy_lazo.data.entity.ServicesEntity;

import java.util.List;

import io.reactivex.Observable;

public interface RestApi {

    Observable<DoctorEntity> login(String email, String password);

    Observable<List<ServicesEntity>> services();

    Observable<List<NewsEntity>> petLoverNews();

}
