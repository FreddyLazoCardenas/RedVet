package com.papps.freddy_lazo.redvet.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;

import butterknife.OnClick;

public class ServicesFragment extends BaseFragment {

    private HomeActivity activity;

    public static Fragment newInstance() {
        return new ServicesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_services, container, false);
    }

    @Override
    public void initUI() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (HomeActivity)getActivity();
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

    @OnClick(R.id.img_header)
    public void imgClicked(){
        activity.onBackPressed();
    }
}
