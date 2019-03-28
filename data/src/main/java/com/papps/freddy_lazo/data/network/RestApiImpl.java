package com.papps.freddy_lazo.data.network;

import android.content.Context;


import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.data.entity.ResponseEntity;
import com.papps.freddy_lazo.data.entity.ServicesEntity;
import com.papps.freddy_lazo.data.network.body.BodyLogin;
import com.papps.freddy_lazo.data.network.response.LoginResponse;
import com.papps.freddy_lazo.data.network.response.ServicesResponse;

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

}
