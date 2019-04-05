package com.papps.freddy_lazo.redvet.view.activity;

import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.Nullable;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.BuildConfig;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerSplashComponent;

import javax.inject.Inject;


public class SplashActivity extends BaseActivity {

    @Inject
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        injectView(this);
        buildInjection();
        setUpHandler();
    }

    private void setUpHandler() {
        Handler ha = new Handler();
        Runnable ru = new Runnable() {
            @Override
            public void run() {
                splashLogic();
            }
        };
        ha.postDelayed(ru,1000);

    }

    private void splashLogic() {
        if(BuildConfig.FLAVOR.equals("doctor")){
            if(preferencesManager.getDoctorCurrentUser()!= null && !preferencesManager.getDoctorCurrentUser().equals("")){
                navigator.navigateToHomeActivity(this);
            }else{
                navigator.navigateToLoginActivity(this);
            }
        }else{
            if(preferencesManager.getPetLoverCurrentUser()!= null && !preferencesManager.getPetLoverCurrentUser().equals("")){
                navigator.navigateToHomeActivity(this);
            }else{
                navigator.navigateToLoginActivity(this);
            }
        }
    }


    private void buildInjection() {
        DaggerSplashComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build().inject(this);
    }

}
