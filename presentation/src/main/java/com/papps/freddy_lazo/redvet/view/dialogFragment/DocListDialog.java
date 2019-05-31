package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.papps.freddy_lazo.redvet.R;

public class DocListDialog extends BaseDialogFragment {

    private OnClickListener listener;

    public static DocListDialog newInstance(OnClickListener listener) {
        return new DocListDialog().setListener(listener);
    }

    private DocListDialog setListener(OnClickListener listener) {
        this.listener = listener;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Que desea hacer con la foto")
                .setItems(R.array.string_array_detail_photo, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            listener.seeDetail();
                            break;
                        case 1:
                            listener.delete();
                            break;
                        case 2:
                            listener.cancel();
                            break;
                    }
                });
        return builder.create();
    }


    public interface OnClickListener {
        void delete();

        void cancel();

        void seeDetail();
    }
}
