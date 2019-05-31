package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.app.Activity;
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
import com.papps.freddy_lazo.redvet.util.DateHelper;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;

import java.text.MessageFormat;
import java.util.Calendar;

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
    @BindView(R.id.tv_appointment_type)
    TextView tvAppointmentType;
    @BindView(R.id.tv_appointment_reason)
    TextView tvAppointmentReason;
    @BindView(R.id.iv_appointment_color)
    ImageView ivAppointmentType;
    @Inject
    PreferencesManager preferencesManager;

    private DoctorAppointmentModel model;
    private HomeActivity activity;
    private RequestInterface listener;
    private static final int CANCEL_REQUEST_CODE = 1;
    private static final int FINISHED_REQUEST_CODE = 2;


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
        displayPhoto(model.getPet().getPhoto_url(), imgPet);
        displayPhoto(model.getPetLover().getPhoto_url(), imgOwner);
        tvPet.setText(model.getPet().getName());
        Calendar calendar = DateHelper.convertToDate(model.getPet().getBirthday());
        tvPetBirthday.setText(MessageFormat.format("{0} {1} {2}", calendar.get(Calendar.DAY_OF_MONTH), DateHelper.getMonthForInt(calendar.get(Calendar.MONTH)).substring(0, 3), calendar.get(Calendar.YEAR)).replaceAll(",", ""));
        String[] split = model.getDate().split("-");
        date.setText(getString(R.string.doctor_date, split[2], split[1], split[0].substring(2)));
        time.setText(model.getTime());
        address.setText(model.getPetLover().getAddress());
        tvPetLoverName.setText(MessageFormat.format("{0} {1}", model.getPetLover().getFirst_name(), model.getPetLover().getLast_name()));
        tvDni.setText(model.getPetLover().getDni());
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
                return R.drawable.emergency;
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

    @OnClick(R.id.btn_cancel)
    public void btnCancelAppointment() {
        navigator.navigateCancelAppointmentActivity(this, model.getId(), CANCEL_REQUEST_CODE);
        // dismiss();
    }

    @OnClick(R.id.iv_appointment_diagnosis)
    public void btnDiagnosisAppointment() {
        navigator.navigateToDiagnoseAppointmentActivity(this, model.toString(), FINISHED_REQUEST_CODE);
        // dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CANCEL_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                listener.successCancelRequest(model.getId());
                dismiss();
            }
        } else if (requestCode == FINISHED_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                listener.successConfirmedRequest(model.getId());
                dismiss();
            }
        }
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

    @OnClick(R.id.chat)
    public void chatClicked() {
        navigator.navigateToChatActivity(activity, model.getId());
    }

    @OnClick(R.id.phone)
    public void phoneClicked() {
        navigator.navigatePhoneCall(activity, model.getPetLover().getPhone());
    }

    public interface RequestInterface {
        void successConfirmedRequest(int id);

        void successCancelRequest(int id);
    }
}
