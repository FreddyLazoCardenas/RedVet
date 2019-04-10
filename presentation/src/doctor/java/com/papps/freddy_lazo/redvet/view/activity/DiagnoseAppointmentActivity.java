package com.papps.freddy_lazo.redvet.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.DiagnoseAppointmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerDiagnoseAppointmentComponent;
import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.presenter.DiagnoseAppointmentPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DiagnoseAppointmentActivity extends BaseActivity implements DiagnoseAppointmentView {

    @Inject
    DiagnoseAppointmentPresenter presenter;
    @Inject
    PreferencesManager preferencesManager;
    @BindView(R.id.et_diagnose)
    EditText etDiagnose;
    @BindView(R.id.img_pet)
    ImageView ivPet;
    @BindView(R.id.tv_pet_name)
    TextView petName;
    @BindView(R.id.tv_pet_birthday)
    TextView petBirthday;
    @BindView(R.id.tv_date)
    TextView appointmentDate;
    @BindView(R.id.tv_time)
    TextView appointmentTime;


    private DoctorAppointmentModel model;

    public static Intent getCallingIntent(BaseActivity activity, String data) {
        return new Intent(activity, DiagnoseAppointmentActivity.class).putExtra("data", data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_appointment);
        injectView(this);
        buildInjection();
        initUI();
    }

    private void buildInjection() {
        DaggerDiagnoseAppointmentComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }


    @OnClick(R.id.btn_finish_appointment)
    public void btnFinishAppointment() {
        presenter.validate();
    }

    @Override
    public void initUI() {
        super.initUI();
        model = DoctorAppointmentModel.toModel(getIntent().getStringExtra("data"));
        presenter.setView(this);
        fillUi();
    }

    private void fillUi() {
        displayPhoto(model.getPetLover().getPhoto_url(), true);
        petName.setText(model.getPet().getName());
        petBirthday.setText(model.getPet().getBirthday());
        appointmentDate.setText(model.getDate());
        appointmentTime.setText(model.getTime());
    }

    public void displayPhoto(String photoUrl, boolean refresh) {
        GlideApp.with(this)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(refresh ? DiskCacheStrategy.NONE : DiskCacheStrategy.ALL)
                .skipMemoryCache(refresh)
                .placeholder(R.drawable.ic_placeholder)
                .load(photoUrl)
                .into(ivPet);
    }

    @Override
    public String getDiagnose() {
        return etDiagnose.getText().toString();
    }

    @Override
    public String getApiToken() {
        return DoctorModel.toModel(preferencesManager.getDoctorCurrentUser()).getApi_token();
    }

    @Override
    public int getAppointmentId() {
        return model.getId();
    }

    @Override
    public void successRequest() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
