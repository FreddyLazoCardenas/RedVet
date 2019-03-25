package com.papps.freddy_lazo.data.network;


import com.papps.freddy_lazo.data.entity.ResponseEntity;
import com.papps.freddy_lazo.data.exception.RedVetException;

import io.reactivex.ObservableEmitter;
import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultCallback<T> implements Callback<T> {

    private final ObservableEmitter emitter;

    DefaultCallback(ObservableEmitter emitter) {
        this.emitter = emitter;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (isSuccessful(emitter, response)) {
            ResponseEntity body = ((ResponseEntity) response.body());
            if (body != null && body.getMessage() != null){
                emitter.onError(new RedVetException(body.getMessage()));
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        if(!emitter.isDisposed()){
            emitter.onError(new RedVetException("Error de servicio"));
        }
    }

    private boolean isSuccessful(ObservableEmitter emitter, Response response) {
        if(emitter.isDisposed()){
            return false;
        }
        return response.isSuccessful();
    }
}