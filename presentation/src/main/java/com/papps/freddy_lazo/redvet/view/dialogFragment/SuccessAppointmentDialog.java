package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papps.freddy_lazo.redvet.R;

import butterknife.OnClick;

public class SuccessAppointmentDialog extends BaseDialogFragment {

    public static SuccessAppointmentDialog newInstance() {
        return new SuccessAppointmentDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_succes_appointment,container,false);
    }

    @OnClick(R.id.btn_appointment)
    public void btnOk(){
        dismiss();
    }
}
