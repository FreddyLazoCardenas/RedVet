package com.papps.freddy_lazo.redvet.view.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.AppointmentActivityView;
import com.papps.freddy_lazo.redvet.internal.bus.Event;
import com.papps.freddy_lazo.redvet.internal.bus.RxBus;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerAppointmentComponent;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentModel;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentObjectModel;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.presenter.AppointmentActivityPresenter;
import com.papps.freddy_lazo.redvet.view.adapter.AppointmentTypeAdapter;
import com.papps.freddy_lazo.redvet.view.adapter.PetLoverPetsAdapter;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AppointmentActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AppointmentActivityView {

    @BindView(R.id.etDatePicker)
    EditText datePicker;
    @BindView(R.id.etTimePicker)
    EditText timePicker;
    @BindView(R.id.et_description)
    EditText etDescription;
    @BindView(R.id.txt_name)
    TextView tvName;
    @BindView(R.id.txt_job)
    TextView tvJob;
    @BindView(R.id.tv_consultation_price)
    TextView tvConsultationPrice;
    @BindView(R.id.rv_appointment)
    RecyclerView rvAppointment;
    @BindView(R.id.rv_pet_lover)
    RecyclerView rvPetLover;
    @Inject
    AppointmentTypeAdapter adapter;
    @Inject
    PetLoverPetsAdapter petsAdapter;
    @Inject
    PreferencesManager preferencesManager;
    @Inject
    AppointmentActivityPresenter presenter;
    @Inject
    RxBus rxBus;
    @BindView(R.id.img_register)
    ImageView ivDoctor;

    private DoctorModel doctorModel;
    private PetLoverModel petLoverModel;


    public static Intent getCallingIntent(BaseActivity activity, String doctorModel) {
        Intent intent = new Intent(activity, AppointmentActivity.class);
        intent.putExtra("doctor", doctorModel);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        injectView(this);
        buildInjection();
        getDoctorData();
        getPetLoverData();
        setUpRv();
        initUI();
    }

    private void getPetLoverData() {
        petLoverModel = PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser());
    }

    private void setUpRv() {
        rvAppointment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvPetLover.setLayoutManager(new LinearLayoutManager(this));
        rvAppointment.setAdapter(adapter);
        rvPetLover.setAdapter(petsAdapter);
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        tvName.setText(doctorModel.getFirst_name());
        initRv();
    }

    private void initRv() {
        List<CreateAppointmentObjectModel> data = new ArrayList<>();
        data.add(new CreateAppointmentObjectModel("Emergencia"));
        data.add(new CreateAppointmentObjectModel("Urgencia"));
        data.add(new CreateAppointmentObjectModel("Consulta"));
        adapter.bindList(data);
        petsAdapter.bindList(petLoverModel.getPetList());
        displayPhoto(true);
    }

    public void displayPhoto(boolean refresh) {
        GlideApp.with(this)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(refresh ? DiskCacheStrategy.NONE : DiskCacheStrategy.ALL)
                .skipMemoryCache(refresh)
                .placeholder(R.drawable.ic_placeholder)
                .load(doctorModel.getPhoto_url())
                .into(ivDoctor);
    }

    private void getDoctorData() {
        doctorModel = DoctorModel.toModel(getIntent().getStringExtra("doctor"));
        tvJob.setText(setJobText());
        tvConsultationPrice.setText(doctorModel.getConsultation_price());
    }

    private int setJobText() {
        switch (doctorModel.getType()) {
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

    private void buildInjection() {
        DaggerAppointmentComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @OnClick({R.id.tilDatePicker, R.id.etDatePicker})
    public void datePicker() {
        navigator.navigateToDatePicker(this);
    }

    @OnClick({R.id.tilTimePicker, R.id.etTimePicker})
    public void timePicker() {
        navigator.navigateToTimePicker(this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        datePicker.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timePicker.setText(String.format("%d:%d:%d", hourOfDay, minute, 00));
    }

    @OnClick(R.id.img_dismiss)
    public void finishImg() {
        finish();
    }

    @OnClick(R.id.btn_book_consultation)
    public void btnBookConsultation() {
        presenter.validateData();
    }

    @Override
    public void successResponse(CreateAppointmentModel data) {
        rxBus.send(new Event.SuccessAppointment());
        finish();
    }

    @Override
    public String getApiToken() {
        return petLoverModel.getApi_token();
    }

    @Override
    public int doctorId() {
        return doctorModel.getUser_id();
    }

    @Override
    public Integer petByPetLoverId() {
        List<PetLoverRegisterModel> list = petsAdapter.getData();
        for (PetLoverRegisterModel petLoverRegisterModel : list) {
            if (petLoverRegisterModel.isSelected()) {
                return petLoverRegisterModel.getId();
            }
        }
        return null;
    }

    @Override
    public String getDate() {
        return datePicker.getText().toString();
    }

    @Override
    public String getTime() {
        return timePicker.getText().toString();
    }

    @Override
    public String getType() {
        List<CreateAppointmentObjectModel> list = adapter.getData();
        for (CreateAppointmentObjectModel createAppointmentObjectModel : list) {
            if (createAppointmentObjectModel.isSelected()) {
                return createAppointmentObjectModel.getName();
            }
        }
        return null;
    }

    @Override
    public String getReason() {
        return etDescription.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
