package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.content.Context;
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
    @BindView(R.id.tv_owner)
    TextView tvPetLoverName;
    @BindView(R.id.txt_dni)
    TextView tvDni;
    @Inject
    PreferencesManager preferencesManager;

    private DoctorAppointmentModel model;
    private HomeActivity activity;


    public static ConfirmedAppointmentDialog newInstance(String data) {
        Bundle args = new Bundle();
        args.putString("data", data);
        ConfirmedAppointmentDialog dialog = new ConfirmedAppointmentDialog();
        dialog.setCancelable(false);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_confirm_appointment, container, false);
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
        displayPhoto(model.getPet().getPhoto(), imgPet);
        displayPhoto(model.getPetLover().getPhoto_url(), imgOwner);
        tvPet.setText(model.getPet().getName());
        tvPetBirthday.setText(model.getPet().getBirthday());
        date.setText(model.getDate());
        time.setText(model.getTime());
        address.setText(model.getPetLover().getAddress());
        tvPetLoverName.setText(model.getPetLover().getFirst_name());
        tvDni.setText(model.getPetLover().getDni());
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
        navigator.navigateCancelAppointmentActivity(activity, model.getId());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @OnClick(R.id.img_dismiss)
    public void btnDismiss() {
        dismiss();
    }

    private void initUI() {
        if (getArguments() != null) {
            activity = (HomeActivity) getActivity();
            String data = getArguments().getString("data");
            model = DoctorAppointmentModel.toModel(data);
            fillUi();
        }
    }

}
