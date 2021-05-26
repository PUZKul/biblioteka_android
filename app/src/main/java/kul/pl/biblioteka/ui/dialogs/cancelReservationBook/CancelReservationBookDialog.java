package kul.pl.biblioteka.ui.dialogs.cancelReservationBook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import kul.pl.biblioteka.R;

public class CancelReservationBookDialog extends AppCompatDialogFragment {

    private DialogCancelReservationBookListener listener;
    private Button cancel;
    private Button back;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cancel_reservation,null);
        initComponents(view);
        setOnClickListeners();
        builder.setTitle(R.string.cancel_reservation_book);
        builder.setView(view);
        return builder.create();
    }

    private void  setOnClickListeners(){
        back.setOnClickListener(onBackClicked);
        cancel.setOnClickListener(onCancelClicked);
    }

    private void initComponents(View view) {
        cancel=view.findViewById(R.id.cancel_reservation_btn_confirm);
        back=view.findViewById(R.id.cancel_reservation_btn_cancel);
    }

    public void setListener(DialogCancelReservationBookListener listener) {
        this.listener = listener;
    }

    private View.OnClickListener onCancelClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onCancel();
        }
    };

    private View.OnClickListener onBackClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
}