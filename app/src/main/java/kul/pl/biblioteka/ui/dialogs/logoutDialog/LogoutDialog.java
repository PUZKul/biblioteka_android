package kul.pl.biblioteka.ui.dialogs.logoutDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import kul.pl.biblioteka.R;

public class LogoutDialog  extends AppCompatDialogFragment {

    private Button yes;
    private Button no;

    private LogoutDialogListener listener;

    public LogoutDialog(LogoutDialogListener listener) {
        this.listener=listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_password_security,null);
        initComponents(view);
        setOnClickListeners();
        builder.setView(view);
        return builder.create();
    }

    private void setOnClickListeners(){
        no.setOnClickListener(onNoCLicked);
        yes.setOnClickListener(onYesClicked);

    }

    private View.OnClickListener onYesClicked =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
            listener.onYesClicked();
        }
    };

    private View.OnClickListener onNoCLicked =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private void initComponents(View view){
        no =view.findViewById(R.id.password_security_btn_cancel);
        yes =view.findViewById(R.id.password_security_btn_confirm);
    }

    public void closeDialog(){
        dismiss();
    }

}
