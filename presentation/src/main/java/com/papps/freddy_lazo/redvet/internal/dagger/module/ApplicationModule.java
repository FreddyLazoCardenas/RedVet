package com.papps.freddy_lazo.redvet.internal.dagger.module;

import android.content.Context;

import com.papps.freddy_lazo.data.repository.UserDataRepository;
import com.papps.freddy_lazo.redvet.AndroidApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;



@Module
public class ApplicationModule {

    private final AndroidApplication mApplication;

    public ApplicationModule(AndroidApplication application) {
        this.mApplication = application;
    }


    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.mApplication;
    }


    @Provides
    @Singleton
    UserDataRepository provideUserDataRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

}

