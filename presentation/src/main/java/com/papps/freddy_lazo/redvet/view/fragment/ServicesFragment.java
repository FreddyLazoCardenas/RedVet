package com.papps.freddy_lazo.redvet.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ServicesFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerRegisterFragmentComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerServicesComponent;
import com.papps.freddy_lazo.redvet.model.ServicesModel;
import com.papps.freddy_lazo.redvet.presenter.ServicesFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.adapter.ServicesAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ServicesFragment extends BaseFragment implements ServicesFragmentView {

    private HomeActivity activity;

    @Inject
    ServicesFragmentPresenter presenter;
    @Inject
    ServicesAdapter adapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static Fragment newInstance() {
        return new ServicesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_services, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    private void buildInjection() {
        DaggerServicesComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        presenter.getServices();
        setUpRv();
    }

    private void setUpRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
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

    @OnClick(R.id.img_header)
    public void imgClicked() {
        activity.onBackPressed();
    }

    @Override
    public void listData(List<ServicesModel> data) {
        Log.d("serviceData",data.toString());
        adapter.bindList(data);
    }
}
