package kul.pl.biblioteka.ui.dialogs.passwordSecurity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import kul.pl.biblioteka.R;

public class PasswordSecurityDialog   extends AppCompatDialogFragment {

    private EditText password;
    private DialogPasswordSecurityListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_password_security,null);
        initComponents(view);
        builder.setView(view)
                .setTitle("Enter  old password")
                .setNegativeButton("Back", onBackClicked)
                .setPositiveButton("Edit data",onEditDataClicked);
        return builder.create();
    }

    public void setListener(DialogPasswordSecurityListener listener) {
        this.listener = listener;
    }

    private DialogInterface.OnClickListener onEditDataClicked=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            listener.applyPassword(password.getText().toString());
        }
    };

    private DialogInterface.OnClickListener onBackClicked=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dismiss();
        }
    };

    private void initComponents(View view){
        password=view.findViewById(R.id.dialog_editText_password);
    }

    private View.OnClickListener onClickedBack=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                dismiss();
        }
    };
}
