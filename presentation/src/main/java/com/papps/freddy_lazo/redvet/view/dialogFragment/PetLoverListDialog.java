package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.papps.freddy_lazo.redvet.R;

public class PetLoverListDialog extends BaseDialogFragment {

    private OnClickListener listener;

    public static PetLoverListDialog newInstance(OnClickListener listener) {
        return new PetLoverListDialog().setListener(listener);
    }

    private PetLoverListDialog setListener(OnClickListener listener) {
        this.listener = listener;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Que desea hacer con el documento")
                .setItems(R.array.string_array_doc_photo, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            listener.seeDetail();
                            break;
                        case 1:
                            listener.cancel();
                            break;
                    }
                });
        return builder.create();
    }


    public interface OnClickListener {
        void cancel();

        void seeDetail();
    }
}
