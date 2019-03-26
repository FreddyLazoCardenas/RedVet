package com.papps.freddy_lazo.domain.interactor;

import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

/**
 * Default observer base class to be used whenever you want default error handling.
 */
public class DefaultObserver<T> extends DisposableObserver<T> implements Observer<T> {

    @Override public void onError(Throwable e) {
        // no-op by default.
    }

    @Override
    public void onComplete() {
        // no-op by default.
    }

    @Override public void onNext(T t) {
        // no-op by default.
    }
}
