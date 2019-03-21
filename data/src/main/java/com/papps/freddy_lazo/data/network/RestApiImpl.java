package com.papps.freddy_lazo.data.network;

import android.content.Context;

import com.papps.freddy_lazo.data.network.network.RestService;

public class RestApiImpl implements RestApi {


    private final Context context;
    private final RestService restService;

    public RestApiImpl(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }
}
