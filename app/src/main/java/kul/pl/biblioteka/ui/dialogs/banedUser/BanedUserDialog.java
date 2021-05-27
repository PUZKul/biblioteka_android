package kul.pl.biblioteka.ui.dialogs.banedUser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import kul.pl.biblioteka.R;

public class BanedUserDialog extends AppCompatDialogFragment {

    private Button cancel;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //todo change layout
        View view = inflater.inflate(R.layout.dialog_cancel_reservation, null);
        initComponents(view);
        setOnClickListeners();
        builder.setView(view);
        return builder.create();
    }

    private void initComponents(View view) {
        //todo init button
    }

    private void setOnClickListeners(){
        //todo set on click listener to back
    }
}