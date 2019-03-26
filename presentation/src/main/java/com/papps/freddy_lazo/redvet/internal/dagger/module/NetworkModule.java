package com.papps.freddy_lazo.redvet.internal.dagger.module;


import android.content.Context;

import com.papps.freddy_lazo.data.network.RestApi;
import com.papps.freddy_lazo.data.network.RestApiImpl;
import com.papps.freddy_lazo.data.network.RestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    RestService provideRestService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://redvet.ctrl.pe/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RestService.class);
    }

    @Provides
    @Singleton
    RestApi provideRestApi(Context context, RestService restService) {
        return new RestApiImpl(context, restService);
    }

}
