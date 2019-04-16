package com.papps.freddy_lazo.data.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface NotificationDao {

    @Query("SELECT * FROM notifications")
    List<NotificationTable> getAll();

    @Query("DELETE FROM notifications")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NotificationTable notification);

}
