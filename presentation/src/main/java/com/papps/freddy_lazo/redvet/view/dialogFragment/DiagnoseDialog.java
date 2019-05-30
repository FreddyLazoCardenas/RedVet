package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.papps.freddy_lazo.redvet.R;

public class DiagnoseDialog extends BaseDialogFragment {

    private OnClickListener listener;

    public static DiagnoseDialog newInstance(OnClickListener listener) {
        return new DiagnoseDialog().setListener(listener);
    }

    private DiagnoseDialog setListener(OnClickListener listener) {
        this.listener = listener;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Seleccione archivo que desee subir")
                .setItems(R.array.string_array_diagnose, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            listener.camera();
                            break;
                        case 1:
                            listener.gallery();
                            break;
                            case 2:
                            listener.pdf();
                            break;
                            case 3:
                            listener.documents();
                            break;
                    }
                });
        return builder.create();
    }


    public interface OnClickListener {
        void camera();

        void gallery();

        void pdf();

        void documents();
    }
}
