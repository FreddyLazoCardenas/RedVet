package com.papps.freddy_lazo.redvet.view.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerAppointmentComponent;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentModel;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentObjectModel;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.model.PetRedVetModel;
import com.papps.freddy_lazo.redvet.model.ServiceDoctorModel;
import com.papps.freddy_lazo.redvet.presenter.AppointmentActivityPresenter;
import com.papps.freddy_lazo.redvet.view.adapter.AppointmentTypeAdapter;
import com.papps.freddy_lazo.redvet.view.adapter.PetCustomerAdapter;
import com.papps.freddy_lazo.redvet.view.adapter.PetLoverPetsAdapter;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AppointmentActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
        , AppointmentActivityView, PetCustomerAdapter.onClickAdapter {

    @BindView(R.id.etDatePicker)
    EditText datePicker;
    @BindView(R.id.hour)
    TextView hour;
    @BindView(R.id.minute)
    TextView minute;
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
    @BindView(R.id.rv_doctor_pets)
    RecyclerView rvDoctorPets;
    @Inject
    AppointmentTypeAdapter adapter;
    @Inject
    PetLoverPetsAdapter petsAdapter;
    @Inject
    PetCustomerAdapter doctorPetAdapter;
    @Inject
    PreferencesManager preferencesManager;
    @Inject
    AppointmentActivityPresenter presenter;
    @BindView(R.id.img_register)
    ImageView ivDoctor;
    @BindView(R.id.iv_check)
    ImageView ivCheck;
    @BindView(R.id.iv_check_footer)
    ImageView ivCheckFooter;
    @BindView(R.id.txt_content)
    TextView tvDescription;
    @BindView(R.id.rating)
    AppCompatRatingBar ratingBar;

    private DoctorModel doctorModel;
    private PetLoverModel petLoverModel;
    private String timeData;


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
        rvDoctorPets.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvPetLover.setLayoutManager(new LinearLayoutManager(this));
        rvAppointment.setAdapter(adapter);
        rvPetLover.setAdapter(petsAdapter);
        rvDoctorPets.setAdapter(doctorPetAdapter);
        doctorPetAdapter.setView(this);
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        timeData = "15:00:00";
        tvName.setText(MessageFormat.format("{0} {1}", doctorModel.getFirst_name(), doctorModel.getLast_name()));
        tvDescription.setText(doctorModel.getDescription());
        ratingBar.setRating(doctorModel.getRate() != null ? doctorModel.getRate() : 5);
        initRv();
        presenter.getPets();
    }

    private void initRv() {
        List<CreateAppointmentObjectModel> data = new ArrayList<>();
        data.add(new CreateAppointmentObjectModel("Emergencia"));
        data.add(new CreateAppointmentObjectModel("Urgencia"));
        data.add(new CreateAppointmentObjectModel("Consulta"));
        data.add(new CreateAppointmentObjectModel("Baño"));
        data.add(new CreateAppointmentObjectModel("Otros"));
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
        tvConsultationPrice.setText(getString(R.string.consultation_price, doctorModel.getConsultation_price()));
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

    @OnClick({R.id.tilDatePicker, R.id.etDatePicker, R.id.btn_calendar})
    public void datePicker() {
        navigator.navigateToDatePicker(this);
    }

    @OnClick(R.id.end_group)
    public void timePicker() {
        navigator.navigateToTimePicker(this);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        ivCheck.setVisibility(View.VISIBLE);
        datePicker.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour.setText(String.valueOf(hourOfDay));
        this.minute.setText(String.valueOf(minute));
        timeData = String.format("%d:%d:%d", hourOfDay, minute, 00);
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
        return ivCheck.getTag().equals("true") ? timeData : "";
    }

    @Override
    public String getType() {
        List<CreateAppointmentObjectModel> list = adapter.getData();
        for (CreateAppointmentObjectModel createAppointmentObjectModel : list) {
            if (createAppointmentObjectModel.isSelected()) {
                return createAppointmentObjectModel.getName();
            }
        }
        return "";
    }

    @Override
    public String getReason() {
        return etDescription.getText().toString();
    }

    @Override
    public void successRequest(List<PetRedVetModel> transform) {
        List<PetRedVetModel> data = new ArrayList<>();
        for (PetLoverRegisterModel model : doctorModel.getPetList()) {
            for (PetRedVetModel dataModel : transform) {
                if (dataModel.getId() == model.getPet_id()) {
                    data.add(dataModel);
                    break;
                }
            }
        }
        doctorPetAdapter.bindList(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @OnClick(R.id.iv_check)
    public void checkClicked() {
        if (ivCheck.getTag().equals("false")) {
            ivCheck.setTag("true");
            ivCheck.setImageResource(R.drawable.ic_check_pink);
        } else {
            ivCheck.setTag("false");
            ivCheck.setImageResource(R.drawable.ic_check_gray);
        }
    }

    @Override
    public void data(List<PetRedVetModel> data) {
        petsAdapter.filteringPets(data);
    }

    @OnClick({R.id.tv_content, R.id.iv_check_footer})
    public void checkFooterClicked() {
        if (ivCheckFooter.getTag().equals("false")) {
            ivCheckFooter.setTag("true");
            ivCheckFooter.setImageResource(R.drawable.ic_check_green);
        } else {
            ivCheckFooter.setTag("false");
            ivCheckFooter.setImageResource(R.drawable.ic_check_gray);
        }
    }

    @OnClick(R.id.tv_services)
    public void services() {
        List<ServiceDoctorModel> data = new ArrayList<>();
        if (doctorModel.getServiceList() != null)
            data = doctorModel.getServiceList();
        navigator.navigateToServicesActivity(this, data, -1);
    }
}
