package com.papps.freddy_lazo.redvet.internal.dagger.component;


import android.content.Context;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;
import com.papps.freddy_lazo.redvet.internal.dagger.module.NetworkModule;
import com.papps.freddy_lazo.redvet.navigation.Navigator;
import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;
import com.papps.freddy_lazo.redvet.internal.dagger.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity activity);

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    Navigator navigator();

    UserRepository userRepository();

    UtilsRepository utilsRepository();

    PreferencesManager preferenceManager();

}
