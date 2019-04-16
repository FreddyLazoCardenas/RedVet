package com.papps.freddy_lazo.redvet.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.papps.freddy_lazo.redvet.AndroidApplication;
import com.papps.freddy_lazo.redvet.interfaces.BaseView;
import com.papps.freddy_lazo.redvet.internal.bus.RxBus;
import com.papps.freddy_lazo.redvet.navigation.Navigator;
import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public abstract class BaseFragment extends Fragment implements BaseView {

    @Inject
    public Navigator navigator;
    @Inject
    RxBus rxBus;

    private Unbinder unbinder;
    private CompositeDisposable mDisposable;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

    }

    AndroidApplication getAndroidApplication() {
        return (AndroidApplication) getActivity().getApplication();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected void showMessage(BaseActivity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
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
