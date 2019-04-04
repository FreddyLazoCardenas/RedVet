package com.papps.freddy_lazo.data.network;

import android.content.Context;


import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.data.entity.NewsEntity;
import com.papps.freddy_lazo.data.entity.ResponseEntity;
import com.papps.freddy_lazo.data.entity.ServicesEntity;
import com.papps.freddy_lazo.data.network.body.BodyDoctorRegister;
import com.papps.freddy_lazo.data.network.body.BodyLogin;
import com.papps.freddy_lazo.data.network.body.BodyPetLoverRegister;
import com.papps.freddy_lazo.data.network.body.BodySearchDoctors;
import com.papps.freddy_lazo.data.network.response.DoctorSearchResponse;
import com.papps.freddy_lazo.data.network.response.LoginResponse;
import com.papps.freddy_lazo.data.network.response.NewsResponse;
import com.papps.freddy_lazo.data.network.response.ServicesResponse;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Response;

public class RestApiImpl implements RestApi {


    private final Context context;
    private final RestService restService;

    public RestApiImpl(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }

    @Override
    public Observable<DoctorEntity> login(String email, String password) {
        return Observable.create(emitter -> restService.login(new BodyLogin(email, password, "asdd", "android")).enqueue(new DefaultCallback<ResponseEntity<LoginResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<LoginResponse>> call, @NonNull Response<ResponseEntity<LoginResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<LoginResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getDoctorEntity());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<ServicesEntity>> services() {
        return Observable.create(emitter -> restService.services().enqueue(new DefaultCallback<ResponseEntity<ServicesResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<ServicesResponse>> call, @NonNull Response<ResponseEntity<ServicesResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<ServicesResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getServicesEntityList());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<NewsEntity>> petLoverNews(String path) {
        return Observable.create(emitter -> restService.petLoverNews("Bearer wuGnaJzXVEFFJkfo", path).enqueue(new DefaultCallback<ResponseEntity<NewsResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<NewsResponse>> call, @NonNull Response<ResponseEntity<NewsResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<NewsResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getNewsEntityList());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<DoctorEntity>> searchDoctors(ArrayList<String> type, ArrayList<Integer> services, ArrayList<Integer> pets, String text) {
        return Observable.create(emitter -> restService.petLoverSearch(new BodySearchDoctors(type, services, pets, text), "Bearer wuGnaJzXVEFFJkfo").enqueue(new DefaultCallback<ResponseEntity<DoctorSearchResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<DoctorSearchResponse>> call, @NonNull Response<ResponseEntity<DoctorSearchResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<DoctorSearchResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getDoctorEntity());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<Void> signUpPetLover(String email, String password, String firstName, String lastName, String dni, String address, String phone, String fcmToken, List<PetRegister> pets) {
        return Observable.create(emitter -> restService.petLoverRegister(new BodyPetLoverRegister(email, password, firstName, lastName, dni, address, phone, fcmToken, "android", pets)).enqueue(new DefaultCallback<ResponseEntity<Void>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<Void>> call, @NonNull Response<ResponseEntity<Void>> response) {
                super.onResponse(call, response);
                ResponseEntity<Void> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<Void> doctorRegister(String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services) {
        return Observable.create(emitter -> restService.doctorRegister(new BodyDoctorRegister(email, password, firstName, lastName, typeDocument, numberDocument, business_name, address, latitude, longitude, consultationPrice, consultationTime, shower_price, shower_time, tuition_number, description, phone, photo, type, fcmToken, attention, device, pets, schedules, services)).enqueue(new DefaultCallback<ResponseEntity<Void>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<Void>> call, @NonNull Response<ResponseEntity<Void>> response) {
                super.onResponse(call, response);
                ResponseEntity<Void> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onComplete();
                }
            }
        }));
    }

}
