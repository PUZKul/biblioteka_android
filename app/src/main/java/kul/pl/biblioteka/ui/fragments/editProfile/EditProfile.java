package kul.pl.biblioteka.ui.fragments.editProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.models.RegistrationUserModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.activity.noInternet.NoInternetActivity;

public class EditProfile extends Fragment implements EditProfileContract.View {

    private EditText editEmail;
    private CheckBox checkBoxEditPassword;
    private TextView textEditPassoword;
    private EditText editPassword;
    private TextView textRepeatEditPassword;
    private EditText repeatEditedPassword;
    private Button cancelBtn;
    private Button saveBtn;
    private ProgressBar progressBar;
    private EditProfileContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        checkBoxStatus();
        initComponents(view);
        setOnClickListeners();
        presenter = new EditProfilePresenter(this);
        return view;
    }

    private void initComponents(View view) {
        checkBoxStatus();
        editEmail = view.findViewById(R.id.editProfile_editText_email);
        checkBoxEditPassword = view.findViewById(R.id.editProfile_checkBox_changePasswordCheckBox);
        textEditPassoword = view.findViewById(R.id.editProfile_textViev_newPassword);
        editPassword = view.findViewById(R.id.editProfile_editText_newPassword);
        textRepeatEditPassword = view.findViewById(R.id.editProfile_textViev_repeatPassword);
        repeatEditedPassword = view.findViewById(R.id.editProfile_editText_repeatPassword);
        progressBar = view.findViewById(R.id.editProfile_progressBar);
        cancelBtn = view.findViewById(R.id.editProfile_btn_cancel);
        saveBtn = view.findViewById(R.id.editProfile_btn_save);
    }

    private void setOnClickListeners() {
        cancelBtn.setOnClickListener(cancelOnClickListener());
        saveBtn.setOnClickListener(saveOnClickListener());
    }

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
                presenter.onSaveClicked(new RegistrationUserModel((
                        null),
                        editPassword.getText().toString()
                        , repeatEditedPassword.getText().toString()
                        , editEmail.getText().toString()));
            }
        };
    }


    @Override
    public void showToast(String message) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void endProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void openMainActivity() {
        Intent intent = new Intent(MainActivity.getAppContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void checkBoxStatus() {
        if (!checkBoxEditPassword.isChecked()) {
            hideEditPasswordsFields();
        } else {
            showEditPasswordsFields();
        }
    }

    @Override
    public void hideEditPasswordsFields() {
        textEditPassoword.setVisibility(View.GONE);
        textRepeatEditPassword.setVisibility(View.GONE);
    }

    @Override
    public void showEditPasswordsFields() {
        textEditPassoword.setEnabled(true);
        editPassword.setEnabled(true);
        textRepeatEditPassword.setEnabled(true);
        repeatEditedPassword.setEnabled(true);
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
    public void getPassword() {

    }

    @Override
    public void getRepeatPassword() {

    }


    public void errorEmailIncorrect() {
        editEmail.setError("Incorrect email!");
    }


    public void errorPasswordIncorrect() {
        editPassword.setError("Password should include at least one numbers, one special character and one letter and has between 8-25 characters");
    }

    public void errorRepeatPasswordAreNotIdentical() {
        repeatEditedPassword.setError("Password are't identical!");
    }


    public void openOnInternetActivity() {
        Intent intent = new Intent(MainActivity.getAppContext(), NoInternetActivity.class);
        startActivity(intent);
    }


}



