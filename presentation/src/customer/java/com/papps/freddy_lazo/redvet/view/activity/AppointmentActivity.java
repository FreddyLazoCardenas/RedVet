package com.papps.freddy_lazo.redvet.view.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerAppointmentComponent;

import butterknife.BindView;
import butterknife.OnClick;

public class AppointmentActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener  , TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.etDatePicker)
    EditText datePicker;


    public static Intent getCallingIntent(BaseActivity activity) {
        return new Intent(activity, AppointmentActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        injectView(this);
        buildInjection();
        initUI();
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

    }
}
