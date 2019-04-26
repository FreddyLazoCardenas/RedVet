package com.papps.freddy_lazo.redvet.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.PetLoverConfirmedView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerCancelAppointmentComponent;
import com.papps.freddy_lazo.redvet.model.CancelAppointmentModel;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.presenter.PetLoverConfirmedAppointmentPresenter;
import com.papps.freddy_lazo.redvet.util.DividerItemDecorator;
import com.papps.freddy_lazo.redvet.view.adapter.CancelAppointmentAdapter;
import com.papps.freddy_lazo.redvet.view.dialogFragment.BaseDialogFragment;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CancelOtherReasonAppointmentDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class CancelAppointmentActivity extends BaseActivity implements CancelOtherReasonAppointmentDialog.SuccessRequest
        , PetLoverConfirmedView, CancelAppointmentAdapter.OnClickAdapter {

    @Inject
    PetLoverConfirmedAppointmentPresenter presenter;
    @Inject
    PreferencesManager preferencesManager;
    @BindView(R.id.rv_cancel)
    RecyclerView rvCancel;
    @Inject
    CancelAppointmentAdapter adapter;

    private int appointmentId;
    private CancelAppointmentModel model;

    public static Intent getCallingIntent(BaseDialogFragment fragment, int appointmentId) {
        return new Intent(fragment.getContext(), CancelAppointmentActivity.class).putExtra("id", appointmentId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_appointment);
        injectView(this);
        buildInjection();
        initUI();
    }

    private void buildInjection() {
        DaggerCancelAppointmentComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public void initUI() {
        super.initUI();
        presenter.setView(this);
        appointmentId = getIntent().getIntExtra("id", -1);
        setUpRV();
    }

    private void setUpRV() {
        rvCancel.setLayoutManager(new LinearLayoutManager(this));
        rvCancel.setAdapter(adapter);
        adapter.setView(this);
        fillAdapter();
        addItemDivider();
    }

    private void addItemDivider() {
       RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(ContextCompat.getDrawable(this, R.drawable.divider));
       rvCancel.addItemDecoration(dividerItemDecoration);
    }

    private void fillAdapter() {
        List<CancelAppointmentModel> data = new ArrayList<>();
        data.add(new CancelAppointmentModel(getString(R.string.cancel_reason_1)));
        data.add(new CancelAppointmentModel(getString(R.string.cancel_reason_2)));
        data.add(new CancelAppointmentModel(getString(R.string.cancel_reason_3)));
        data.add(new CancelAppointmentModel("Otros"));
        adapter.bindList(data);
    }

    @OnClick(R.id.img_header)
    public void finishView() {
        finish();
    }

    @Override
    public String getApiToken() {
        return PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser()).getApi_token();
    }

    @Override
    public String getReason() {
        return (model != null && model.isSelected()) ? model.getText() : "";
    }

    @Override
    public int getAppointmentId() {
        return appointmentId;
    }

    @Override
    public void showReasonError(String message) {
        showErrorMessage(message);
    }

    @Override
    public void hideReasonError() {

    }

    @Override
    public void successRequest() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void success() {
        successRequest();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void data(CancelAppointmentModel data) {
        model = data;
    }

    @OnClick(R.id.btn_send)
    public void btnClick() {
        if (!getReason().isEmpty() && !getReason().equals("Otros"))
            presenter.sendRequest();
        else if (getReason().equals("Otros"))
            navigator.navigateOtherReasonCancelAppointment(this, appointmentId, this);
        else
            showErrorMessage(getString(R.string.text_required_field));
    }
}
