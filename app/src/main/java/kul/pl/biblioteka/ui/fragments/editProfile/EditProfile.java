package kul.pl.biblioteka.ui.fragments.editProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.activity.noInternet.NoInternetActivity;

public class EditProfile extends AppCompatActivity implements EditProfileContract.View {

    private EditText editEmail;
    private EditText editPassword;
    private EditText repeatEditedPassword;
    private Button cancelBtn;
    private Button saveBtn;
    private ProgressBar progressBar;
    private EditProfileContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_profile);
        initComponents();
        setOnClickListeners();
        presenter = new EditProfilePresenter((EditProfileContract.View) this,getApplicationContext());
    }

    private void initComponents() {
        editEmail=findViewById(R.id.editProfile_editText_email);
        editPassword=findViewById(R.id.editProfile_editText_newPassword);
        repeatEditedPassword=findViewById(R.id.editProfile_editText_repeatPassword);
        cancelBtn=findViewById(R.id.editProfile_btn_cancel);
        saveBtn=findViewById(R.id.editProfile_btn_save);
    }

    private void setOnClickListeners() {
        cancelBtn.setOnClickListener(cancelOnClickListener);
        //saveBtn.setOnClickListener(saveOnClickListener);
    }

    private View.OnClickListener cancelOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, MainActivity.class);
                startActivity(intent);
            }
        };



//    private View.OnClickListener saveOnClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.onSaveClicked(new RegistrationUserModel((
//                            nick potrzebny a ni ma
//                         passwordText.getText().toString()
//                    ,    repeatPasswordText.getText().toString()
//                    ,   editEmail.getText().toString()));
//            }
//        };

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(EditProfile.this, MainActivity.class);
        startActivity(intent);
    }



    @Override
    public void errorEmailIncorrect() {
        editEmail.setError("Incorrect email!");
    }

    @Override
    public void errorEmailIsEmpty() {
        editEmail.setError("Email is empty!");
    }

    @Override
    public void errorPasswordIsEmpty() {
        editPassword.setError("Password is empty!");
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
    public void openOnInternetActivity() {
        Intent intent = new Intent(EditProfile.this, NoInternetActivity.class);
        startActivity(intent);
    }
}



