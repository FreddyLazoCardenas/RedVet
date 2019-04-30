package com.papps.freddy_lazo.redvet.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ServicesFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerServicesActivityComponent;
import com.papps.freddy_lazo.redvet.model.ServiceDoctorModel;
import com.papps.freddy_lazo.redvet.model.ServicesModel;
import com.papps.freddy_lazo.redvet.presenter.ServicesFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.adapter.ServicesAdapter;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ServicesActivity extends BaseActivity implements ServicesFragmentView {

    @Inject
    ServicesFragmentPresenter presenter;
    @Inject
    ServicesAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<ServiceDoctorModel> servicesData;

    public static Intent getCallingIntent(BaseFragment fragment, List<ServiceDoctorModel> data) {
        return new Intent(fragment.getContext(), ServicesActivity.class).putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) data);
    }

    public static Intent getCallingIntent(BaseActivity activity, List<ServiceDoctorModel> data) {
        return new Intent(activity, ServicesActivity.class).putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) data).putExtra("canClick", false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        injectView(this);
        buildInjection();
        initUI();
    }

    private void buildInjection() {
        DaggerServicesActivityComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        getServicesData();
        getCanClick();
        setUpRv();
        presenter.getServices();
    }

    private void getCanClick() {
        adapter.setCanClick(getIntent().getBooleanExtra("canClick", true));
    }

    private void getServicesData() {
        servicesData = getIntent().getParcelableArrayListExtra("data");
    }

    private void setUpRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void listData(List<ServicesModel> data) {
        for (ServiceDoctorModel model : servicesData) {
            for (ServicesModel bindData : data) {
                if (model.getService_id() == bindData.getId()) {
                    bindData.setState(true);
                    bindData.setResponseId(model.getId());
                }
            }
        }
        adapter.bindList(data);
    }


    @Override
    public void finish() {
        Intent data = new Intent();
        data.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) adapter.getData());
        setResult(RESULT_OK, data);
        super.finish();
    }

    @OnClick(R.id.img_header)
    public void imageClicked() {
        finish();
    }
}
