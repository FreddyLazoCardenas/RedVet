package com.papps.freddy_lazo.redvet.view.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.DatePicker;

import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;
import com.papps.freddy_lazo.redvet.view.dialogFragment.BaseDialogFragment;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends BaseDialogFragment {


    private BaseActivity activity;

    public static DatePickerFragment newInstance(BaseActivity activity) {
        DatePickerFragment f = new DatePickerFragment();
        f.activity = activity;
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        // Use the current date as the default date in the picker
        DatePickerDialog pickerDialog = new DatePickerDialog(activity, (DatePickerDialog.OnDateSetListener) activity, mYear, mMonth, mDay);
       // pickerDialog.getDatePicker().setMaxDate(new Date().getTime());
      /*  try {
            pickerDialog.getDatePicker().setMinDate(DateHelper.createTimeStamp("1905-01-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        return pickerDialog;
    }

}
