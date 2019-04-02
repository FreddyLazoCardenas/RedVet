package com.papps.freddy_lazo.redvet.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerRegisterComponent;

public class RegisterActivity extends BaseActivity {

    public static Intent getCallingIntent(Activity activity) {
        return new Intent(activity, RegisterActivity.class);
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
        DaggerRegisterComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public void initUI() {
        navigator.navigateToRegisterFragment(this);
    }

}
