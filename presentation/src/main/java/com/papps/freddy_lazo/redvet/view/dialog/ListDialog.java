package com.papps.freddy_lazo.redvet.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.support.annotation.NonNull;

import com.papps.freddy_lazo.redvet.R;

import butterknife.ButterKnife;


public class ListDialog extends Dialog {

    public ListDialog(@NonNull Context context) {
        super(context);
        if(getWindow()!=null){
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_camera_options);
        ButterKnife.bind(this);
        setCancelable(false);
    }



}
