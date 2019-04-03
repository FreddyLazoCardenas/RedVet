package com.papps.freddy_lazo.redvet.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.papps.freddy_lazo.redvet.AndroidApplication;
import com.papps.freddy_lazo.redvet.interfaces.BaseView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.ApplicationComponent;
import com.papps.freddy_lazo.redvet.model.ServicesModel;
import com.papps.freddy_lazo.redvet.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements BaseView {

    @Inject
    protected Navigator navigator;

    /*    @Nullable
        @BindView(R.id.v_progress)
        View vProgress;

        @Nullable
        @BindView(R.id.toolbar)
        Toolbar toolbar;
        */
    protected boolean isStopped;
    private List<ServicesModel> data =  new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected void injectView(Activity activity) {
        ButterKnife.bind(activity);
    }

    public boolean isStopped() {
        return isStopped;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isStopped = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        isStopped = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isStopped = true;
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showErrorMessage(String message) {
    }

    @Override
    public void initUI() {

    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    public void data(List<ServicesModel> data) {
        this.data = data;
    }

    public List<ServicesModel> getData() {
        return data;
    }
}
