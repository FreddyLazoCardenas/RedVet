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
import com.papps.freddy_lazo.redvet.interfaces.AppointmentFragmentView;
import com.papps.freddy_lazo.redvet.internal.bus.Event;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerAppointmentFragmentComponent;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentObjectModel;
import com.papps.freddy_lazo.redvet.model.PetLoverAppointmentModel;
import com.papps.freddy_lazo.redvet.presenter.AppointmentFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.adapter.AppointmentAdapter;
import com.papps.freddy_lazo.redvet.view.adapter.AppointmentHeaderAdapter;
import com.papps.freddy_lazo.redvet.view.dialogFragment.ConfirmedAppointmentDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class AppointmentFragment extends BaseFragment implements AppointmentFragmentView, AppointmentHeaderAdapter.onClickAdapter, AppointmentAdapter.onClickAdapter, ConfirmedAppointmentDialog.RequestInterface {


    @BindView(R.id.rv_appointments)
    RecyclerView rvAppointments;
    @BindView(R.id.rv_header)
    RecyclerView rvHeader;
    @Inject
    AppointmentFragmentPresenter presenter;
    @Inject
    AppointmentAdapter adapter;
    @Inject
    AppointmentHeaderAdapter headerAdapter;

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
        subscribeBus();
        setUpRv();
        initUI();
    }

    @Override
    public void onStart() {
        super.onStart();
        subscribeBus();
    }

    @Override
    public void onStop() {
        super.onStop();
        unsubscribeBus();
    }

    @Override
    protected Consumer<Object> getBusAction() {
        return event -> {
            if (event instanceof Event.NotificationEvent) {
                Log.d("getBusAction", "llego el evento al fragment");
            }
        };
    }

    private void setUpRv() {
        rvAppointments.setLayoutManager(new LinearLayoutManager(activity));
        rvAppointments.setAdapter(adapter);
        adapter.setView(this);
        rvHeader.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        rvHeader.setAdapter(headerAdapter);
        headerAdapter.setView(this);
        List<CreateAppointmentObjectModel> data = new ArrayList<>();
        data.add(new CreateAppointmentObjectModel("Pendientes","pending"));
        data.add(new CreateAppointmentObjectModel("Confirmadas","confirmed"));
        data.add(new CreateAppointmentObjectModel("Finalizadas","finished"));
        headerAdapter.bindList(data);
    }

    private void buildInjection() {
        DaggerAppointmentFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
        unsubscribeBus();
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

    @Override
    public void dataAdapter(List<CreateAppointmentObjectModel> data) {
        adapter.setFiltering(isDataFiltering(data));
        if (adapter.isFiltering())
            adapter.bindFilterList(getAppointmentStatus(data));
        else
            adapter.bindList(null);
    }

    private boolean isDataFiltering(List<CreateAppointmentObjectModel> data) {
        for (CreateAppointmentObjectModel model : data) {
            if (model.isSelected()) {
                return true;
            }
        }
        return false;
    }

    private String getAppointmentStatus(List<CreateAppointmentObjectModel> data) {
        String appointmentName = "";
        for (CreateAppointmentObjectModel model : data) {
            if (model.isSelected()) {
                appointmentName = model.getSearchName();
            }
        }
        return appointmentName;
    }

    @Override
    public void itemClicked(PetLoverAppointmentModel data) {
        Log.d("itemClicked", data.getStatus());
        switch (data.getStatus()) {
            case "pending":
                navigator.navigatePendingDialog(activity, data.toString());
                break;
            case "finished":
                navigator.navigateFinishedDialog(activity, data.toString());
                break;
            case "confirmed":
                navigator.navigateConfirmedDialog(activity, data.toString(), this);
                break;

        }
    }

    @Override
    public void successCancelRequest(int id) {
        adapter.removeAppointment(id);
    }
}
