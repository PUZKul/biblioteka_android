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
        initComponents(view);
        checkBoxStatus();
        setOnClickListeners();
        presenter = new EditProfilePresenter(this);
        return view;
    }

    private void initComponents(View view) {
        editEmail = view.findViewById(R.id.editProfile_editText_email2);
        checkBoxEditPassword = view.findViewById(R.id.editProfile_checkBox_changePasswordCheckBox2);
        textEditPassoword = view.findViewById(R.id.editProfile_textViev_newPassword2);
        editPassword = view.findViewById(R.id.editProfile_editText_newPassword2);
        textRepeatEditPassword = view.findViewById(R.id.editProfile_textViev_repeatPassword2);
        repeatEditedPassword = view.findViewById(R.id.editProfile_editText_repeatPassword2);
        progressBar = view.findViewById(R.id.editProfile_progressBar2);
        cancelBtn = view.findViewById(R.id.editProfile_btn_cancel2);
        saveBtn = view.findViewById(R.id.editProfile_btn_save2);
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
        editPassword.setVisibility(View.GONE);
        repeatEditedPassword.setVisibility(View.GONE);
    }

    @Override
    public void showEditPasswordsFields() {
        textEditPassoword.setVisibility(View.VISIBLE);
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



