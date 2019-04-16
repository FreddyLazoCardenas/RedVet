package com.papps.freddy_lazo.data.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {NotificationTable.class}, version = LocalDatabase.VERSION, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract NotificationDao getNotificationDao();
}