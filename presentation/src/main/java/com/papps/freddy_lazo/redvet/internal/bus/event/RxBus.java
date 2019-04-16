package com.papps.freddy_lazo.redvet.internal.bus.event;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

@Singleton
public class RxBus {

    @Inject
    public RxBus() {
    }

    private final Subject<Object> mSubject = PublishSubject.create();

    public void send(Object o) {
        mSubject.onNext(o);
    }

    public Observable<Object> toObservable() {
        return mSubject;
    }
}
