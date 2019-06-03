package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.PendingAppointmentDialogView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerPendingAppointmentDialogComponent;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.model.PetLoverAppointmentModel;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.presenter.PetLoverPendingAppointmentPresenter;
import com.papps.freddy_lazo.redvet.util.DateHelper;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;

import java.text.MessageFormat;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class PendingAppointmentDialog extends BaseDialogFragment implements PendingAppointmentDialogView {

    @BindView(R.id.img_pet)
    ImageView imgPet;
    @BindView(R.id.img_owner)
    ImageView imgOwner;
    @BindView(R.id.txt_name)
    TextView tvPet;
    @BindView(R.id.txt_pet_birthday)
    TextView tvPetBirthday;
    @BindView(R.id.txt_date)
    TextView date;
    @BindView(R.id.txt_time)
    TextView time;
    @BindView(R.id.txt_address)
    TextView address;
    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;
    @BindView(R.id.tv_doctor_job)
    TextView tvDoctorJob;
    @BindView(R.id.txt_dni)
    TextView tvDni;
    @BindView(R.id.group_buttons)
    android.support.constraint.Group gButtons;
    @BindView(R.id.tv_appointment_type)
    TextView tvAppointmentType;
    @BindView(R.id.tv_appointment_reason)
    TextView tvAppointmentReason;
    @BindView(R.id.iv_appointment_color)
    ImageView ivAppointmentType;
    @Inject
    PetLoverPendingAppointmentPresenter presenter;
    @Inject
    PreferencesManager preferencesManager;


    private PetLoverAppointmentModel model;
    private HomeActivity activity;
    private PetLoverModel petLoverModel;
    private PetLoverRegisterModel pet;

    public static PendingAppointmentDialog newInstance(String data) {
        Bundle args = new Bundle();
        args.putString("data", data);
        PendingAppointmentDialog dialog = new PendingAppointmentDialog();
        dialog.setCancelable(false);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_pl_pending, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }


    private void buildInjection() {
        DaggerPendingAppointmentDialogComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    private void fillUi() {
        gButtons.setVisibility(View.GONE);
        displayPhoto(pet.getPhoto_url(), imgPet);
        displayPhoto(model.getDoctor().getPhoto_url(), imgOwner);
        tvPet.setText(pet.getName());
        Calendar calendar = DateHelper.convertToDate(pet.getBirthday());
        tvPetBirthday.setText(MessageFormat.format("{0} {1} {2}", calendar.get(Calendar.DAY_OF_MONTH), DateHelper.getMonthForInt(calendar.get(Calendar.MONTH)).substring(0, 3), calendar.get(Calendar.YEAR)).replaceAll(",",""));
        String[] split = model.getDate().split("-");
        date.setText(getString(R.string.doctor_date, split[2], split[1], split[0].substring(2)));
        time.setText(model.getTime());
        address.setText(model.getDoctor().getAddress());
        tvDoctorName.setText(MessageFormat.format("{0} {1}", model.getDoctor().getFirst_name(), model.getDoctor().getLast_name()));
        tvDni.setText(model.getDoctor().getNumber_document());
        tvDoctorJob.setText(setJobText(model.getDoctor().getType()));
        tvAppointmentType.setText(model.getType());
        tvAppointmentReason.setText(model.getReason());
        ivAppointmentType.setImageResource(setImageAppointmentType());
    }

    private int setImageAppointmentType() {
        switch (model.getType()) {
            case "Emergencia":
                return R.drawable.emergency;
            case "Urgencia":
                return R.drawable.urgency;
            case "Consulta":
                return R.drawable.simple_appointment;
            case "Baño":
                return R.drawable.barber_shower_appointment;
            case "Otros":
                return R.drawable.other_appointment;
            default:
                return R.drawable.barber_shower_appointment;

        }
    }

    private int setJobText(String type) {
        switch (type) {
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

    public void displayPhoto(String photoUrl, ImageView img) {
        GlideApp.with(activity)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_placeholder)
                .load(photoUrl)
                .into(img);
    }

    @OnClick(R.id.img_dismiss)
    public void dismissImg() {
        dismiss();
    }

    @OnClick(R.id.tv_confirm)
    public void confirm() {
        dismiss();
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
    public void initUI() {
        if (getArguments() != null) {
            activity = (HomeActivity) getActivity();
            String data = getArguments().getString("data");
            model = PetLoverAppointmentModel.toModel(data);
            petLoverModel = PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser());
            presenter.setView(this);
            getPetData(model.getPet_by_pet_lover_id());
            fillUi();
        }
    }

    private void getPetData(String petId) {
        for (PetLoverRegisterModel petLoverRegisterModel : petLoverModel.getPetList()) {
            if (petLoverRegisterModel.getId() == Integer.valueOf(petId)) {
                pet = petLoverRegisterModel;
                break;
            }
        }
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

    @OnClick(R.id.chat)
    public void chatClicked() {
        navigator.navigateToChatActivity(activity, model.getId());
    }

    @OnClick(R.id.phone)
    public void phoneClicked() {
        navigator.navigatePhoneCall(activity, model.getDoctor().getPhone());
    }

    @OnClick(R.id.txt_address)
    public void goToAppointment() {
        navigator.navigateToNavigation(activity, model.getDoctor().getLatitude(), model.getDoctor().getLongitude());
    }

}
