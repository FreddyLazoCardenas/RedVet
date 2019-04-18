package com.papps.freddy_lazo.redvet.view.dialogFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papps.freddy_lazo.redvet.R;

public class DoctorNotificationConfirmedDialog extends BaseDialogFragment {

    public static DoctorNotificationConfirmedDialog newInstance(String notification) {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_doctor, container, false);
    }
}
