package com.papps.freddy_lazo.redvet.view.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.DatePicker;

import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;
import com.papps.freddy_lazo.redvet.view.dialogFragment.BaseDialogFragment;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends BaseDialogFragment {


    private BaseActivity activity;
    private BaseFragment fragment;
    private Context context;

    public static DatePickerFragment newInstance(BaseActivity activity) {
        DatePickerFragment f = new DatePickerFragment();
        f.activity = activity;
        f.context = activity;
        return f;
    }

    public static DatePickerFragment newInstance(BaseFragment fragment) {
        DatePickerFragment f = new DatePickerFragment();
        f.fragment = fragment;
        f.context = fragment.getContext();
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
        return new DatePickerDialog(context, activity != null ? (DatePickerDialog.OnDateSetListener) activity : (DatePickerDialog.OnDateSetListener) fragment, mYear, mMonth, mDay);
    }

}
