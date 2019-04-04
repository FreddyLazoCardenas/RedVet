package com.papps.freddy_lazo.redvet.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.BuildConfig;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerProfileFragmentComponent;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;

import javax.inject.Inject;

import butterknife.OnClick;

public class ProfileFragment extends BaseFragment {


    @Inject
    PreferencesManager preferencesManager;

    private HomeActivity activity;

    public static Fragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void initUI() {
        getUserData();
    }

    private void getUserData() {
            PetLoverModel petLoverModel = PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser());
            Log.d("das","dasd");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    private void buildInjection() {
        DaggerProfileFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (HomeActivity) getActivity();
        initUI();
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @OnClick(R.id.btn_log_out)
    public void logOut() {
        navigator.navigateToLoginActivity(activity);
    }
}
