package com.papps.freddy_lazo.data.local.database;

import com.papps.freddy_lazo.data.entity.NotificationEntity;
import com.papps.freddy_lazo.data.entity.mapper.NotificationTableDataMapper;
import com.papps.freddy_lazo.data.exception.RedVetException;

import java.util.List;

import io.reactivex.Observable;

public class DatabaseImpl implements Database {

    private LocalDatabase localDatabase;

    public DatabaseImpl(LocalDatabase localDatabase) {
        this.localDatabase = localDatabase;
    }

    @Override
    public Observable<Void> saveNotification(NotificationEntity entities) {
        return Observable.create(emitter -> {
            localDatabase.getNotificationDao().insert(NotificationTableDataMapper.transform(entities));
            if (!emitter.isDisposed()) {
                emitter.onComplete();
            }
        });
    }

    @Override
    public Observable<List<NotificationEntity>> getNotifications() {
        return Observable.create(emitter -> {
            List<NotificationTable> stores = localDatabase.getNotificationDao().getAll();
            if (!emitter.isDisposed()) {
                if (stores == null || stores.isEmpty()) {
                    emitter.onError(new RedVetException("No data"));
                } else {
                    emitter.onNext(NotificationTableDataMapper.transform(stores));
                    emitter.onComplete();
                }
            }
        });
    }
}
