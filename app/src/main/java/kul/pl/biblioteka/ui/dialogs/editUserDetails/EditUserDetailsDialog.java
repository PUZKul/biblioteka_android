package kul.pl.biblioteka.ui.dialogs.editUserDetails;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.models.EditUserModel;
import kul.pl.biblioteka.models.UserModel;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialog;
import kul.pl.biblioteka.ui.dialogs.passwordSecurity.PasswordSecurityDialog;
import kul.pl.biblioteka.validators.EditUserValidator;

public class EditUserDetailsDialog extends AppCompatDialogFragment {

    private Button back;
    private Button edit;
    private EditText firstName;
    private EditText lastName;
    private EditText phone;
    private EditText address;
    private EditUserDetailsListener listener;

    public EditUserDetailsDialog(EditUserDetailsListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_user_details, null);
        initComponents(view);
        setOnClickListener();
        builder.setView(view);
        return builder.create();
    }

    private void initComponents(View view) {
        back = view.findViewById(R.id.dialog_edit_user_button_cancel);
        edit = view.findViewById(R.id.dialog_edit_user_button_edit);
        firstName = view.findViewById(R.id.dialog_edit_user_edit_text_first_name);
        lastName = view.findViewById(R.id.dialog_edit_user_edit_text_last_name);
        phone = view.findViewById(R.id.dialog_edit_user_edit_text_phone);
        address = view.findViewById(R.id.dialog_edit_user_edit_text_address);
    }

    private void setOnClickListener() {
        back.setOnClickListener(onBackClicked);
        edit.setOnClickListener(onEditClicked);
    }

    private View.OnClickListener onEditClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(EditUserValidator.validateUser(getUserDetails())){
                //listener.callbackForEditUserDetails(getUserDetails());
                PasswordSecurityDialog dialog=new PasswordSecurityDialog();
                dialog.show(getFragmentManager(),"");
            }else {
                showToast();
            }
        }
    };

    private void showToast(){
        Toast.makeText(getContext(), getContext().getString(R.string.one_of_fields_is_empty), Toast.LENGTH_LONG).show();
    }

    private EditUserModel getUserDetails(){
        return new EditUserModel(
                firstName.getText().toString(),
                lastName.getText().toString(),
                address.getText().toString(),
                phone.getText().toString());
    }

    private View.OnClickListener onBackClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
}