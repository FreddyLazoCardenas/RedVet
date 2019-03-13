package com.papps.freddy_lazo.redvet.view.activity;

import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.Nullable;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerSplashComponent;


public class SplashActivity extends BaseActivity {

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
        Runnable ru = () -> navigator.navigateToLoginActivity(SplashActivity.this);
        ha.postDelayed(ru,1000);
    }

    private void buildInjection() {
        DaggerSplashComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build().inject(this);
    }

}
