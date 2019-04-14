package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.papps.freddy_lazo.redvet.R;

public class PetListDialog extends BaseDialogFragment {

    private OnClickListener listener;

    public static PetListDialog newInstance(OnClickListener listener) {
        return new PetListDialog().setListener(listener);
    }

    private PetListDialog setListener(OnClickListener listener) {
        this.listener = listener;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Que desea hacer con la mascota")
                .setItems(R.array.string_array_pet, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            listener.edit();
                            break;
                        case 1:
                            listener.delete();
                            break;
                    }
                });
        return builder.create();
    }


    public interface OnClickListener {
        void delete();

        void edit();
    }
}
