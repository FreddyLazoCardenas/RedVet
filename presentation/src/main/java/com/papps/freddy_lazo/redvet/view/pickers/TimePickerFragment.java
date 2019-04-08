package com.papps.freddy_lazo.redvet.view.pickers;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;
import com.papps.freddy_lazo.redvet.view.dialogFragment.BaseDialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends BaseDialogFragment {


    private BaseActivity activity;

    public static TimePickerFragment newInstance(BaseActivity activity) {
        TimePickerFragment f = new TimePickerFragment();
        f.activity = activity;
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minute = rightNow.get(Calendar.MINUTE);
        // Use the current date as the default date in the picker
        TimePickerDialog timePickerDialog = new TimePickerDialog(activity, (TimePickerDialog.OnTimeSetListener) activity, hour, minute, false);
        return timePickerDialog;
    }

}
