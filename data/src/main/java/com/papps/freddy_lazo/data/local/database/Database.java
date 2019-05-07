package com.papps.freddy_lazo.data.local.database;

import com.papps.freddy_lazo.data.entity.NotificationEntity;

import java.util.List;

import io.reactivex.Observable;

public interface Database {

    Observable<Void> saveNotification(NotificationEntity entities);

    Observable<Void> deleteSpecificNotification(Integer id);

    Observable<List<NotificationEntity>>  getNotifications();
}
