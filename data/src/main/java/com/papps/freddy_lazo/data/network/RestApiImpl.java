package com.papps.freddy_lazo.data.network;

import android.content.Context;


import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public class RestApiImpl implements RestApi {


    private final Context context;
    private final RestService restService;

    public RestApiImpl(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }


    @Override
    public Observable<ValidateQrEntity> login(long customerUserId, String code) {
        return Observable.create(emitter -> restService.validateQrCode(customerUserId, new BodyValidateQrCode(code)).enqueue(new DefaultCallback<ResponseEntity<QrValidateResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<QrValidateResponse>> call, @NonNull Response<ResponseEntity<QrValidateResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<QrValidateResponse> body = response.body();
                if (body != null && body.getError() == null) {
                    emitter.onNext(body.getBody().getValidateQrEntity());
                    emitter.onComplete();
                }
            }
        }));
    }
}
