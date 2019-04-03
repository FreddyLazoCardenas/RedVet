package com.papps.freddy_lazo.data.network;

import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.data.entity.NewsEntity;
import com.papps.freddy_lazo.data.entity.PetRegisterEntity;
import com.papps.freddy_lazo.data.entity.ServicesEntity;
import com.papps.freddy_lazo.domain.model.PetRegister;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface RestApi {

    Observable<DoctorEntity> login(String email, String password);

    Observable<List<ServicesEntity>> services();

    Observable<List<NewsEntity>> petLoverNews(String path);

    Observable<List<DoctorEntity>> searchDoctors(ArrayList<String> type, ArrayList<Integer> services, ArrayList<Integer> pets, String text);

    Observable<Void> signUpPetLover(String email, String password, String firstName, String lastName, String dni, String address, String phone, String fcmToken, List<PetRegister> pets);

}
