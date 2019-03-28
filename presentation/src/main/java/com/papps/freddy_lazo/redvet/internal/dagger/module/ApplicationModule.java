package com.papps.freddy_lazo.redvet.internal.dagger.module;

import android.content.Context;

import com.papps.freddy_lazo.data.executor.JobExecutor;
import com.papps.freddy_lazo.data.repository.UserDataRepository;
import com.papps.freddy_lazo.data.repository.UtilsDataRepository;
import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;
import com.papps.freddy_lazo.redvet.AndroidApplication;
import com.papps.freddy_lazo.redvet.UIThread;

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
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    UserRepository provideUserDataRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Provides
    @Singleton
    UtilsRepository provideUserDataRepository(UtilsDataRepository utilsDataRepository) {
        return utilsDataRepository;
    }

}

