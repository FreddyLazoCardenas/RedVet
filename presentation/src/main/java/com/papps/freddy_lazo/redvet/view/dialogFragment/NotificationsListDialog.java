package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.papps.freddy_lazo.redvet.R;

public class NotificationsListDialog extends BaseDialogFragment {

    private OnClickListener listener;

    public static NotificationsListDialog newInstance(OnClickListener listener) {
        return new NotificationsListDialog().setListener(listener);
    }

    private NotificationsListDialog setListener(OnClickListener listener) {
        this.listener = listener;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Que desea hacer con la notificación")
                .setItems(R.array.array_not, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            listener.markAsRead();
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
        void markAsRead();

        void delete();

        void cancel();
    }
}
