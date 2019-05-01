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
import com.papps.freddy_lazo.redvet.interfaces.NotificationFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerNotificationsFragmentComponent;
import com.papps.freddy_lazo.redvet.model.NotificationModel;
import com.papps.freddy_lazo.redvet.presenter.NotificationFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.adapter.NotificationAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class NotificationsFragment extends BaseFragment implements NotificationFragmentView {

    @Inject
    NotificationFragmentPresenter presenter;
    @Inject
    NotificationAdapter adapter;
    @BindView(R.id.rv_notifications)
    RecyclerView rvNotifications;
    private HomeActivity activity;

    public static Fragment newInstance() {
        return new NotificationsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    private void buildInjection() {
        DaggerNotificationsFragmentComponent.builder()
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
    public void initUI() {
        setUpRv();
        presenter.setView(this);
        presenter.getNotificationList();
    }

    private void setUpRv() {
        rvNotifications.setLayoutManager(new LinearLayoutManager(activity));
        rvNotifications.setAdapter(adapter);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void successRequest(List<NotificationModel> data) {
        Collections.reverse(data);
        adapter.bindList(data);
    }
}
