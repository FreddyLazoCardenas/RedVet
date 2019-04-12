package com.papps.freddy_lazo.redvet.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.PetLoverConfirmedView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerCancelAppointmentComponent;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.presenter.PetLoverConfirmedAppointmentPresenter;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CancelOtherReasonAppointmentDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class CancelAppointmentActivity extends BaseActivity implements CancelOtherReasonAppointmentDialog.SuccessRequest, PetLoverConfirmedView {

    @Inject
    PetLoverConfirmedAppointmentPresenter presenter;
    @Inject
    PreferencesManager preferencesManager;
    @BindView(R.id.item_1)
    TextView item1;
    @BindView(R.id.item_2)
    TextView item2;
    @BindView(R.id.item_3)
    TextView item3;

    private String reason;
    private int appointmentId;

    public static Intent getCallingIntent(BaseActivity activity, int appointmentId) {
        return new Intent(activity, CancelAppointmentActivity.class).putExtra("id", appointmentId);
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
    }

    @OnClick(R.id.img_header)
    public void finishView() {
        finish();
    }

    @OnClick(R.id.item_1)
    public void item1() {
        reason = item1.getText().toString();
        presenter.sendRequest();
    }

    @OnClick(R.id.item_2)
    public void item2() {
        reason = item2.getText().toString();
        presenter.sendRequest();
    }

    @OnClick(R.id.item_3)
    public void item3() {
        reason = item3.getText().toString();
        presenter.sendRequest();
    }

    @OnClick(R.id.item_4)
    public void item4() {
        navigator.navigateOtherReasonCancelAppointment(this, appointmentId, this);
    }


    @Override
    public String getApiToken() {
        return PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser()).getApi_token();
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public int getAppointmentId() {
        return appointmentId;
    }

    @Override
    public void showReasonError(String message) {

    }

    @Override
    public void hideReasonError() {

    }

    @Override
    public void successRequest() {

    }

    @Override
    public void success() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
