package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.papps.freddy_lazo.redvet.R;

public class FinishAppointmentDialog extends BaseDialogFragment {

    private OnClickListener listener;
    private int id;

    public static FinishAppointmentDialog newInstance(OnClickListener listener, int id) {
        return new FinishAppointmentDialog().setListener(listener,id);
    }

    private FinishAppointmentDialog setListener(OnClickListener listener,int id) {
        this.listener = listener;
        this.id = id;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Que desea hacer con esta cita")
                .setItems(R.array.string_array_not, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            listener.delete(id);
                            break;
                        case 1:
                            listener.cancel();
                            break;
                    }
                });
        return builder.create();
    }


    public interface OnClickListener {
        void delete(int id);

        void cancel();
    }
}
