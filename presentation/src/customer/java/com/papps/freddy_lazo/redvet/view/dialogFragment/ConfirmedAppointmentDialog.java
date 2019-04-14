package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.app.Activity;
import android.content.Intent;
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
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerConfirmedAppointmentDialogComponent;
import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;
import com.papps.freddy_lazo.redvet.model.PetLoverAppointmentModel;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfirmedAppointmentDialog extends BaseDialogFragment {

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
    @Inject
    PreferencesManager preferencesManager;

    private PetLoverAppointmentModel model;
    private PetLoverRegisterModel pet;
    private PetLoverModel petLoverModel;
    private HomeActivity activity;
    private RequestInterface listener;

    private static final int CANCEL_REQUEST_CODE = 1;


    public static ConfirmedAppointmentDialog newInstance(String data, RequestInterface listener) {
        Bundle args = new Bundle();
        args.putString("data", data);
        ConfirmedAppointmentDialog dialog = new ConfirmedAppointmentDialog();
        dialog.listener = listener;
        dialog.setCancelable(false);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_pl_confirmed, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }


    private void buildInjection() {
        DaggerConfirmedAppointmentDialogComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }


    private void fillUi() {
        displayPhoto(pet.getPhoto(), imgPet);
        displayPhoto(model.getDoctor().getPhoto_url(), imgOwner);
        tvPet.setText(pet.getName());
        tvPetBirthday.setText(pet.getBirthday());
        date.setText(model.getDate());
        time.setText(model.getTime());
        address.setText(model.getDoctor().getAddress());
        tvDoctorName.setText(model.getDoctor().getFirst_name());
        tvDni.setText(model.getDoctor().getNumber_document());
        tvDoctorJob.setText(model.getDoctor().getType());
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

    @OnClick(R.id.btn_cancel)
    public void btnCancelAppointment() {
        navigator.navigateCancelAppointmentActivity(this, model.getId(),CANCEL_REQUEST_CODE);
    }

    @OnClick(R.id.img_dismiss)
    public void btnDismiss() {
        dismiss();
    }

    private void initUI() {
        if (getArguments() != null) {
            activity = (HomeActivity) getActivity();
            String data = getArguments().getString("data");
            model = PetLoverAppointmentModel.toModel(data);
            petLoverModel = PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser());
            //presenter.setView(this);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CANCEL_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                listener.successCancelRequest(model.getId());
                dismiss();
            }
        }
    }
    @OnClick(R.id.chat)
    public void chatClicked() {
        navigator.navigateToChatActivity(activity, model.getId());
    }
    @OnClick(R.id.phone)
    public void phoneClicked() {
        navigator.navigatePhoneCall(activity, model.getDoctor().getPhone());
    }

    public interface RequestInterface {
        void successCancelRequest(int id);
    }
}
