package com.papps.freddy_lazo.redvet.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.papps.freddy_lazo.redvet.AndroidApplication;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.BaseView;
import com.papps.freddy_lazo.redvet.internal.bus.RxBus;
import com.papps.freddy_lazo.redvet.internal.dagger.component.ApplicationComponent;
import com.papps.freddy_lazo.redvet.model.ServicesModel;
import com.papps.freddy_lazo.redvet.navigation.Navigator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class BaseActivity extends AppCompatActivity implements BaseView {

    @Inject
    protected Navigator navigator;

    @Inject
    RxBus rxBus;

    @Nullable
    @BindView(R.id.v_progress)
    View vProgress;

    /* @Nullable
     @BindView(R.id.toolbar)
     Toolbar toolbar;
     */
    protected boolean isStopped;
    private CompositeDisposable mDisposable;
    private List<ServicesModel> data = new ArrayList<>();


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

    public void showLoading() {
        if (vProgress != null) vProgress.setVisibility(View.VISIBLE);
    }

    public boolean isLoading() {
        return vProgress != null && vProgress.getVisibility() == View.VISIBLE;
    }

    public void hideLoading() {
        if (vProgress != null) vProgress.setVisibility(View.GONE);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(this, message);
    }

    @Override
    public void initUI() {

    }

    @Override
    public void showErrorNetworkMessage(String message) {
        showMessage(this, message);
    }

    public List<ServicesModel> getData() {
        return data;
    }

    protected void showMessage(BaseActivity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    public Navigator getNavigator() {
        return navigator;
    }

    void subscribeBus() {
        Consumer<Object> action = getBusAction();
        if (action != null) {
            if (mDisposable == null) {
                mDisposable = new CompositeDisposable();
            }
            mDisposable.add(rxBus.toObservable().subscribe(action));
        } else {
            throw new NullPointerException("Action must not be null. Override getBusAction method to provide an action to the bus.");
        }
    }

    void unsubscribeBus() {
        mDisposable.clear();
    }

    Consumer<Object> getBusAction() {
        return null;
    }
}
