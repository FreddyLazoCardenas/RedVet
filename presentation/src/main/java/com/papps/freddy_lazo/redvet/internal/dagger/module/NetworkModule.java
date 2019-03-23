package com.papps.freddy_lazo.redvet.internal.dagger.module;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.papps.freddy_lazo.data.network.RestApi;
import com.papps.freddy_lazo.data.network.RestApiImpl;
import com.papps.freddy_lazo.data.network.RestService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    @Named("rest")
    RestService provideRestService(OkHttpClient.Builder httpClientBuilder) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://redvet.ctrl.pe/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RestService.class);
    }

    @Provides
    @Singleton
    @Named("restApi")
    RestApi provideRestApi(Context context, @Named("rest") RestService restService) {
        return new RestApiImpl(context, restService);
    }

}
