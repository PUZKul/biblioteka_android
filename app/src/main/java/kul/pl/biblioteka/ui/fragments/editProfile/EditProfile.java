package kul.pl.biblioteka.ui.fragments.editProfile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.models.EditUserModel;
import kul.pl.biblioteka.models.RegistrationUserModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialogListener;
import kul.pl.biblioteka.ui.dialogs.passwordSecurity.DialogListener;
import kul.pl.biblioteka.ui.dialogs.passwordSecurity.PasswordSecurityDialog;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialog;

public class EditProfile extends Fragment implements EditProfileContract.View, DialogListener, NoInternetDialogListener {

    private EditText editEmail;
    private CheckBox checkBoxEditPassword;
    private TextView textEditPassword;
    private EditText editPassword;
    private TextView textRepeatEditPassword;
    private EditText repeatEditedPassword;
    private Button cancelBtn;
    private Button saveBtn;
    private ProgressBar progressBar;
    private EditProfileContract.Presenter presenter;
    private  NoInternetDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        initComponents(view);
        setOnClickListeners();
        presenter = new EditProfilePresenter(this);
        presenter.setUserDetails();
        hideEditPasswordsFields();
        dialog =new  NoInternetDialog(this);
        return view;
    }

    private void initComponents(View view) {
        editEmail = view.findViewById(R.id.editProfile_editText_email2);
        checkBoxEditPassword = view.findViewById(R.id.editProfile_checkBox_changePasswordCheckBox2);
        textEditPassword = view.findViewById(R.id.editProfile_textViev_newPassword2);
        editPassword = view.findViewById(R.id.editProfile_editText_newPassword2);
        textRepeatEditPassword = view.findViewById(R.id.editProfile_textViev_repeatPassword2);
        repeatEditedPassword = view.findViewById(R.id.editProfile_editText_repeatPassword2);
        progressBar = view.findViewById(R.id.editProfile_progresBar);
        cancelBtn = view.findViewById(R.id.editProfile_btn_cancel2);
        saveBtn = view.findViewById(R.id.editProfile_btn_save2);
    }

    private void setOnClickListeners() {
        cancelBtn.setOnClickListener(cancelOnClickListener());
        saveBtn.setOnClickListener(saveOnClickListener());
        checkBoxEditPassword.setOnClickListener(checkBoxOnClickListener);
    }

    private View.OnClickListener checkBoxOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            checkBoxStatus();
        }
    };

    private View.OnClickListener cancelOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.getAppContext(), MainActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener saveOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxEditPassword.isChecked())
                    presenter.onSaveClicked(new RegistrationUserModel(
                            editPassword.getText().toString()
                            , repeatEditedPassword.getText().toString()
                            , editEmail.getText().toString()));
                else
                    presenter.onSaveClicked(new RegistrationUserModel(
                            editPassword.getText().toString()
                            , ""
                            , ""));
            }
        };
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void openMainActivity() {
        //todo change on fragment
        Intent intent = new Intent(MainActivity.getAppContext(), MainActivity.class);
        startActivity(intent);
    }

    private void checkBoxStatus() {
        if (!checkBoxEditPassword.isChecked()) {
            hideEditPasswordsFields();
        } else {
            showEditPasswordsFields();
        }
    }

    private void hideEditPasswordsFields() {
        textEditPassword.setVisibility(View.GONE);
        textRepeatEditPassword.setVisibility(View.GONE);
        editPassword.setVisibility(View.GONE);
        repeatEditedPassword.setVisibility(View.GONE);
    }

    private void showEditPasswordsFields() {
        textEditPassword.setVisibility(View.VISIBLE);
        textRepeatEditPassword.setVisibility(View.VISIBLE);
        editPassword.setVisibility(View.VISIBLE);
        repeatEditedPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEmail(String email) {
        editEmail.setText(email);
    }

    @Override
    public String getEmail() {
        return editEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return editPassword.getText().toString();
    }

    @Override
    public String getRepeatPassword() {
        return repeatEditedPassword.getText().toString();
    }

    @Override
    public boolean isCheckedBoxEditPassword() {
        return checkBoxEditPassword.isChecked();
    }

    @Override
    public void openDialog() {
        PasswordSecurityDialog dialog=new PasswordSecurityDialog();
        dialog.setListener(this);
        dialog.show(getActivity().getSupportFragmentManager(),"password dialog");
    }

    @Override
    public void errorEmailIncorrect() {
        editEmail.setError("Incorrect email!");
    }


    @Override
    public void errorPasswordIncorrect() {
        editPassword.setError("Password should include at least one numbers, one special character and one letter and has between 8-25 characters");
    }

    @Override
    public void errorRepeatPasswordAreNotIdentical() {
        repeatEditedPassword.setError("Password are't identical!");
    }

    @Override
    public void openNoInternetDialog() {
        dialog.show(getActivity().getSupportFragmentManager(),"No Internet dialog");
        dialog.setOnClickedBack();
    }

    @Override
    public void applyPassword(String password) {
        presenter.changeUserData(new EditUserModel(
                editEmail.getText().toString(),
                editPassword.getText().toString(),
                password
        ));
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    presenter.setUserDetails();
                    dialog.closeDialog();
            }
        },5000);
    }

}



