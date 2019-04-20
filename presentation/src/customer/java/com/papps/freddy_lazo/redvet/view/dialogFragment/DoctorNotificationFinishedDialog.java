package com.papps.freddy_lazo.redvet.view.dialogFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.DoctorNotificationFinishedView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerDoctorNotificationFinishedDialogFragmentComponent;
import com.papps.freddy_lazo.redvet.model.RedVetDetailAppointmentModel;
import com.papps.freddy_lazo.redvet.presenter.DoctorNotificationFinishedPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DoctorNotificationFinishedDialog extends BaseDialogFragment implements DoctorNotificationFinishedView {


    @BindView(R.id.txt_name)
    TextView doctorName;
    @BindView(R.id.txt_job)
    TextView doctorJob;
    @BindView(R.id.iv_notification)
    ImageView ivDoctor;
    @BindView(R.id.rating)
    AppCompatRatingBar ratingBar;
    @Inject
    DoctorNotificationFinishedPresenter presenter;

    public static final String NOTIFICATION_DATA = "data";
    private HomeActivity activity;
    private RedVetDetailAppointmentModel model;


    public static DoctorNotificationFinishedDialog newInstance(String notification) {
        DoctorNotificationFinishedDialog dialog = new DoctorNotificationFinishedDialog();
        Bundle args = new Bundle();
        args.putString(NOTIFICATION_DATA, notification);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_doctor_finished_notification, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    private void buildInjection() {
        DaggerDoctorNotificationFinishedDialogFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            initUI();
        }
    }

    private int setJobText() {
        switch (model.getDoctor().getType()) {
            case "clinic":
                return R.string.doctor_clinic_type;
            case "vet":
                return R.string.doctor_vet_type;
            case "other":
                return R.string.doctor_other_type;
            default:
                return R.string.doctor_clinic_type;
        }
    }

    private void fillUi() {
        doctorName.setText(model.getDoctor().getFirst_name());
        doctorJob.setText(setJobText());
        loadImage(model.getDoctor().getPhoto_url());
    }

    private void loadImage(String photo) {
        GlideApp.with(activity)
                .asBitmap()
                .dontAnimate()
                .placeholder(R.drawable.ic_placeholder)
                .load(photo != null ? photo : "")
                .into(ivDoctor);
    }

    private RedVetDetailAppointmentModel getModel(String data) {
        return RedVetDetailAppointmentModel.toModel(data);
    }

    @OnClick(R.id.btn_send)
    public void btnOk() {
        presenter.sendRequest();
    }

    @Override
    public String getApiToken() {
        return activity.getModel().getApi_token();
    }

    @Override
    public int getAppointmentId() {
        return model.getId();
    }

    @Override
    public int getQualification() {
        return (int) ratingBar.getRating();
    }

    @Override
    public void successRequest() {
        dismiss();
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        activity = (HomeActivity) getActivity();
        model = getModel(Objects.requireNonNull(getArguments()).getString(NOTIFICATION_DATA));
        fillUi();
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
}
