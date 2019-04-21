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

    public static Intent getCallingIntent(BaseFragment fragment, String data) {
        return new Intent(fragment.getContext(), ServicesActivity.class).putExtra("data", data);
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
        presenter.getServices();
        setUpRv();
    }

    private void setUpRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void listData(List<ServicesModel> data) {
        adapter.bindList(data);
    }


    @Override
    public void finish() {
        Intent data = new Intent();
        data.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) adapter.getData());
        setResult(RESULT_OK,data);
        super.finish();
    }

    @OnClick(R.id.img_header)
    public void imageClicked(){
        finish();
    }
}
