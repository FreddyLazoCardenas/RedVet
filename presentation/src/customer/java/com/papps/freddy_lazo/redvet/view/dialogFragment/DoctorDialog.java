package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerDoctorDialogFragmentComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerLoginFragmentComponent;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DoctorDialog extends BaseDialogFragment {


    @BindView(R.id.txt_name)
    TextView name;
    @BindView(R.id.txt_job)
    TextView job;

    public static final String DOCTOR_DATA = "doctor_model";

    private DoctorModel doctorModel;
    private HomeActivity activity;

    public static DoctorDialog newInstance(String doctor) {
        Bundle args = new Bundle();
        args.putString(DOCTOR_DATA, doctor);
        DoctorDialog dialog = new DoctorDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_doctor, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    private void buildInjection() {
        DaggerDoctorDialogFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            activity = (HomeActivity) getActivity();
            doctorModel = getDoctorModel(getArguments().getString(DOCTOR_DATA));
            fillUi();
        }
    }

    private void fillUi() {
        name.setText(doctorModel.getFirst_name());
        job.setText(setJobText());
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

    private DoctorModel getDoctorModel(String data) {
        return DoctorModel.toModel(data);
    }

    @OnClick(R.id.btn_call)
    public void callDoctor() {
        String phone = doctorModel.getPhone();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }

    @OnClick(R.id.btn_whatsapp)
    public void whatsAppDoctor() {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "51" + doctorModel.getPhone() + "&text=" + "Hola "));
        if (sendIntent.resolveActivity(activity.getPackageManager()) == null) {
            Toast.makeText(getContext(), "Instalar whatsApp por favor", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(sendIntent);
    }

    @OnClick(R.id.btn_appointment)
    public void doctorAppointment() {
        navigator.navigateToAppointmentActivity(activity, doctorModel.toString());
        dismiss();
    }

    @OnClick(R.id.img_dismiss)
    public void dismissDialog() {
        dismiss();
    }

}