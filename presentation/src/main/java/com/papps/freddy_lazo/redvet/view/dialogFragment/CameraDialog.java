package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.papps.freddy_lazo.redvet.R;

public class CameraDialog extends BaseDialogFragment {

    private OnClickListener listener;

    public static CameraDialog newInstance(OnClickListener listener) {
        return new CameraDialog().setListener(listener);
    }

    private CameraDialog setListener(OnClickListener listener) {
        this.listener = listener;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Seleccione de dónde quiere obtener su foto")
                .setItems(R.array.string_array_camera, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            listener.camera();
                            break;
                        case 1:
                            listener.gallery();
                            break;
                    }
                });
        return builder.create();
    }


    public interface OnClickListener {
        void camera();

        void gallery();
    }
}
