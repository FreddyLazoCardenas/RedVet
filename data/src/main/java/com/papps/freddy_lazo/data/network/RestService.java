package com.papps.freddy_lazo.data.network;

import com.papps.freddy_lazo.data.entity.ResponseEntity;
import com.papps.freddy_lazo.data.network.body.BodyLogin;
import com.papps.freddy_lazo.data.network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestService {

    @POST("login")
    Call<ResponseEntity<LoginResponse>> login(@Body BodyLogin bodyLogin);
}
