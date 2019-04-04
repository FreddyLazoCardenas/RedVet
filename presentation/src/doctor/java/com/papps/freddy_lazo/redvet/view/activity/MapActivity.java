package com.papps.freddy_lazo.redvet.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerMapComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerRegisterComponent;

public class MapActivity extends BaseActivity {

    public static Intent getCallingIntent(BaseActivity activity) {
        return new Intent(activity, MapActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        injectView(this);
        buildInjection();
        initUI();
    }

    private void buildInjection() {
        DaggerMapComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }
}
