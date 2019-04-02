package com.papps.freddy_lazo.data.network;

import com.papps.freddy_lazo.data.entity.ResponseEntity;
import com.papps.freddy_lazo.data.network.body.BodyLogin;
import com.papps.freddy_lazo.data.network.body.BodyPetLoverRegister;
import com.papps.freddy_lazo.data.network.response.LoginResponse;
import com.papps.freddy_lazo.data.network.response.NewsResponse;
import com.papps.freddy_lazo.data.network.response.ServicesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestService {

    @POST("login")
    Call<ResponseEntity<LoginResponse>> login(@Body BodyLogin bodyLogin);

    @POST("pet-lover/sign-up")
    Call<ResponseEntity<LoginResponse>> petLoverRegister(@Body BodyPetLoverRegister bodyLogin);

    @POST("doctor/sign-up")
    Call<ResponseEntity<LoginResponse>> doctorRegister(@Body BodyPetLoverRegister bodyLogin);

    @GET("pet-lover/news")
    Call<ResponseEntity<NewsResponse>> petLoverNews(@Header("Authorization") String auth);

    @GET("services")
    Call<ResponseEntity<ServicesResponse>> services();

}
