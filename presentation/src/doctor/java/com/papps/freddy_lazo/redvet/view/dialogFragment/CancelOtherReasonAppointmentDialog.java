package com.papps.freddy_lazo.redvet.view.dialogFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ConfirmedAppointmentDialogView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerCancelAppointmentOtherReasonDialogComponent;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.presenter.DoctorConfirmedAppointmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;
import com.papps.freddy_lazo.redvet.view.activity.CancelAppointmentActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class CancelOtherReasonAppointmentDialog extends BaseDialogFragment implements ConfirmedAppointmentDialogView {

    @BindView(R.id.et_description)
    EditText etDescription;
    @BindView(R.id.til_description)
    TextInputLayout textInputLayout;
    @Inject
    DoctorConfirmedAppointmentPresenter presenter;
    @Inject
    PreferencesManager preferencesManager;
    private SuccessRequest listener;

    public static final String APPOINTMENT_ID = "id";
    private CancelAppointmentActivity activity;
    private int appointmentId;

    public static CancelOtherReasonAppointmentDialog newInstance(int id, SuccessRequest listener) {
        CancelOtherReasonAppointmentDialog dialog = new CancelOtherReasonAppointmentDialog();
        dialog.listener =  listener;
        Bundle args = new Bundle();
        args.putInt(APPOINTMENT_ID, id);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_other_reason_cancel_appointment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    private void buildInjection() {
        DaggerCancelAppointmentOtherReasonDialogComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            activity = (CancelAppointmentActivity) getActivity();
            appointmentId = getArguments().getInt(APPOINTMENT_ID, -1);
            initUI();
        }
    }

    @OnClick(R.id.send_btn)
    public void sendBtn() {
        presenter.validate();
    }

    @Override
    public String getApiToken() {
        return DoctorModel.toModel(preferencesManager.getDoctorCurrentUser()).getApi_token();
    }

    @Override
    public String getReason() {
        return etDescription.getText().toString();
    }

    @Override
    public int getAppointmentId() {
        return appointmentId;
    }

    @Override
    public void showReasonError(String message) {
        showError(textInputLayout, etDescription, message);
    }

    @Override
    public void hideReasonError() {
        hideError(textInputLayout);
    }

    @Override
    public void successRequest() {
        listener.success();
        dismiss();
    }

    @Override
    public void initUI() {
        presenter.setView(this);
    }

    @Override
    public Context context() {
        return activity;
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(activity, message);
    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    private void showError(TextInputLayout inputLayout, EditText editText, String message) {
        inputLayout.setErrorEnabled(true);
        inputLayout.setError(message);
        editText.requestFocus();
    }

    private void hideError(TextInputLayout inputLayout) {
        if (inputLayout.isErrorEnabled()) {
            inputLayout.setError(null);
            inputLayout.setErrorEnabled(false);
        }
    }

    public interface SuccessRequest {
        void success();
    }

}
