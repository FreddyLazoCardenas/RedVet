package com.papps.freddy_lazo.redvet;

import android.app.Application;

import com.papps.freddy_lazo.redvet.internal.dagger.component.ApplicationComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerApplicationComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.module.ApplicationModule;

public class AndroidApplication extends Application {

    private ApplicationComponent mApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();

    }

    private void initializeInjector() {
        this.mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }

}
