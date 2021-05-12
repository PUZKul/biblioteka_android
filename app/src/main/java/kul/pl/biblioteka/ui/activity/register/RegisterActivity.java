package kul.pl.biblioteka.ui.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.models.RegistrationUserModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.activity.login.LoginActivity;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialog;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialogListener;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityContract.View , NoInternetDialogListener {

    private Button registrationBtn;
    private TextView loginText;
    private EditText nickText;
    private EditText emailText;
    private EditText passwordText;
    private EditText repeatPasswordText;
    private ProgressBar progressBar;
    private RegisterActivityContract.Presenter presenter;
    private NoInternetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initComponents();
        setOnClickListeners();
        dialog=new NoInternetDialog(this);
        presenter = new RegisterActivityPresenter(this,getApplicationContext());
    }

    private void setOnClickListeners() {
        registrationBtn.setOnClickListener(registrationOnClickListener);
        loginText.setOnClickListener(loginOnClickListener);
    }

    private View.OnClickListener loginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener registrationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.onRegisterClicked(new RegistrationUserModel(
                    nickText.getText().toString()
                    , passwordText.getText().toString()
                    , repeatPasswordText.getText().toString()
                    , emailText.getText().toString()));
        }
    };

    private void initComponents() {
        registrationBtn = findViewById(R.id.registration_btn_register);
        loginText = findViewById(R.id.registration_textView_login);
        nickText = findViewById(R.id.registration_editText_nick);
        emailText = findViewById(R.id.registration_editText_email);
        passwordText = findViewById(R.id.registration_editText_password);
        repeatPasswordText = findViewById(R.id.registration_editText_repeatPassword);
        progressBar = findViewById(R.id.registration_progressBar);
    }

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
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void errorNickIncorrect() {
        nickText.setError("Password should consist of numbers and letters and has between 3-12 characters");
    }

    @Override
    public void errorNickIsEmpty() {
        nickText.setError("Nick is empty!");
    }

    @Override
    public void errorEmailIncorrect() {
        emailText.setError("Incorrect email!");
    }

    @Override
    public void errorEmailIsEmpty() {
        emailText.setError("Email is empty!");
    }

    @Override
    public void errorPasswordIsEmpty() {
        passwordText.setError("Password is empty!");
    }

    @Override
    public void errorPasswordIncorrect() {
        passwordText.setError("Password should include at least one numbers, one special character and one letter and has between 8-25 characters");
    }

    @Override
    public void errorRepeatPasswordAreNotIdentical() {
        repeatPasswordText.setError("Password are't identical!");
    }

    @Override
    public void openOnInternetDialog() {
        dialog.show(getSupportFragmentManager(),getString(R.string.no_internet_dialog));
        dialog.setOnClickedBack();
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.onRegisterClicked(new RegistrationUserModel(
                        nickText.getText().toString()
                        , passwordText.getText().toString()
                        , repeatPasswordText.getText().toString()
                        , emailText.getText().toString()));
                dialog.closeDialog();
            }
        },5000);
    }

    @Override
    public void showToast() {
        Toast.makeText(this, R.string.no_internet_message, Toast.LENGTH_LONG).show();
    }
}