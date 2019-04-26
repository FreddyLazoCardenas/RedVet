package com.papps.freddy_lazo.redvet.view.dialogFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.RedVetDetailAppointmentModel;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;

import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class DoctorNotificationConfirmedDialog extends BaseDialogFragment {


    @BindView(R.id.txt_name)
    TextView doctorName;
    @BindView(R.id.txt_job)
    TextView doctorJob;
    @BindView(R.id.tv_appointment_date)
    TextView appointmentDate;
    @BindView(R.id.tv_appointment_time)
    TextView appointmentTime;
    @BindView(R.id.iv_notification)
    ImageView ivDoctor;

    public static final String NOTIFICATION_DATA = "data";
    private HomeActivity activity;
    private RedVetDetailAppointmentModel model;


    public static DoctorNotificationConfirmedDialog newInstance(String notification) {
        DoctorNotificationConfirmedDialog dialog = new DoctorNotificationConfirmedDialog();
        Bundle args = new Bundle();
        args.putString(NOTIFICATION_DATA, notification);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_doctor_confirmed_notification, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            activity = (HomeActivity) getActivity();
            model = getModel(getArguments().getString(NOTIFICATION_DATA));
            fillUi();
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
        doctorName.setText(MessageFormat.format("{0} {1}", model.getDoctor().getFirst_name(), model.getDoctor().getLast_name()));
        doctorJob.setText(setJobText());
        Calendar calendar = convertToDate(model.getDate());
        appointmentDate.setText(MessageFormat.format("{0} {1}", Objects.requireNonNull(calendar).get(Calendar.DAY_OF_MONTH), getMonthForInt(calendar.get(Calendar.MONTH))));
        appointmentTime.setText(model.getTime());
        loadImage(model.getDoctor().getPhoto_url());
    }

    private Calendar convertToDate(String txt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            sdf.parse(txt);
            return sdf.getCalendar();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    private RedVetDetailAppointmentModel getModel(String data) {
        return RedVetDetailAppointmentModel.toModel(data);
    }

    private void loadImage(String photo) {
        GlideApp.with(activity)
                .asBitmap()
                .dontAnimate()
                .placeholder(R.drawable.ic_placeholder)
                .load(photo != null ? photo : "")
                .into(ivDoctor);
    }

    @OnClick(R.id.btn_call)
    public void btnCall() {
        activity.getNavigator().navigatePhoneCall(activity, model.getDoctor().getPhone());
    }

    @OnClick(R.id.btn_whatsapp)
    public void btnWhatsApp() {
        activity.getNavigator().navigateWhatsApp(activity, model.getDoctor().getPhone());
    }

    @OnClick(R.id.btn_ok)
    public void btnOk() {
        dismiss();
    }
}
