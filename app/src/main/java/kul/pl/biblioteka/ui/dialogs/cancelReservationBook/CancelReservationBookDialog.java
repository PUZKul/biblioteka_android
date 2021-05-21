package kul.pl.biblioteka.ui.dialogs.cancelReservationBook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import kul.pl.biblioteka.R;

public class CancelReservationBookDialog extends AppCompatDialogFragment {

    private DialogCancelReservationBookListener listener;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.
                setTitle("Cancel reservation book")
                .setNegativeButton("Back", onBackClicked)
                .setPositiveButton("Cancel", onCancelClicked);
        return builder.create();
    }

    public void setListener(DialogCancelReservationBookListener listener) {
        this.listener = listener;
    }

    private DialogInterface.OnClickListener onCancelClicked = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            listener.onCancel();
        }
    };

    private DialogInterface.OnClickListener onBackClicked = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dismiss();
        }
    };
}
