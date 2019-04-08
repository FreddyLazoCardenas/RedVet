package com.papps.freddy_lazo.redvet.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.AppointmentFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerAppointmentFragmentComponent;
import com.papps.freddy_lazo.redvet.model.PetLoverAppointmentModel;
import com.papps.freddy_lazo.redvet.presenter.AppointmentFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.adapter.AppointmentAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AppointmentFragment extends BaseFragment implements AppointmentFragmentView {


    @BindView(R.id.rv_appointments)
    RecyclerView rvAppointments;
    @Inject
    AppointmentFragmentPresenter presenter;
    @Inject
    AppointmentAdapter adapter;

    private HomeActivity activity;

    public static Fragment newInstance() {
        return new AppointmentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quote, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (HomeActivity) getActivity();
        setUpRv();
        initUI();
    }

    private void setUpRv() {
        rvAppointments.setLayoutManager(new LinearLayoutManager(activity));
        rvAppointments.setAdapter(adapter);
    }

    private void buildInjection() {
        DaggerAppointmentFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }


    @Override
    public void initUI() {
        presenter.setView(this);
        presenter.sendRequest();
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(activity, message);
    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @Override
    public String getApiToken() {
        return activity.getModel().getApi_token();
    }

    @Override
    public void successRequest(List<PetLoverAppointmentModel> data) {
        adapter.bindList(data);
    }
}
