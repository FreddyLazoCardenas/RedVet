package com.papps.freddy_lazo.redvet.internal.dagger.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.papps.freddy_lazo.data.local.database.Database;
import com.papps.freddy_lazo.data.local.database.DatabaseImpl;
import com.papps.freddy_lazo.data.local.database.LocalDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalDatabaseModule {

    @Singleton
    @Provides
    LocalDatabase providesLocalDatabase(Context context) {
        return Room.databaseBuilder(context, LocalDatabase.class, "red-vet-db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    Database providesDatabase(LocalDatabase localDatabase) {
        return new DatabaseImpl(localDatabase);
    }
}
