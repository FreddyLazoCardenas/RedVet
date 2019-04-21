package com.papps.freddy_lazo.redvet.view.pickers;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;
import com.papps.freddy_lazo.redvet.view.dialogFragment.BaseDialogFragment;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;

import java.util.Calendar;

public class TimePickerFragment extends BaseDialogFragment {


    private BaseActivity activity;
    private BaseFragment fragment;
    private Context context;

    public static TimePickerFragment newInstance(BaseActivity activity) {
        TimePickerFragment f = new TimePickerFragment();
        f.activity = activity;
        f.context = activity;
        return f;
    }

    public static TimePickerFragment newInstance(BaseFragment fragment) {
        TimePickerFragment f = new TimePickerFragment();
        f.fragment = fragment;
        f.context = fragment.getContext();
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minute = rightNow.get(Calendar.MINUTE);
        // Use the current date as the default date in the picker
        return new TimePickerDialog(context, activity != null ? (TimePickerDialog.OnTimeSetListener) activity : (TimePickerDialog.OnTimeSetListener) fragment, hour, minute, false);
    }

}
