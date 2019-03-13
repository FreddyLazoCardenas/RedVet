package com.papps.freddy_lazo.redvet.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerLoginComponent;

public class LoginActivity extends BaseActivity {

    public static Intent getCallingIntent(Activity activity) {
        return new Intent(activity, LoginActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        injectView(this);
        buildInjection();
        initUI();
    }

    private void buildInjection() {
        DaggerLoginComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public void initUI() {
        navigator.navigateToLoginFragment(this);
    }
}
