package kul.pl.biblioteka.ui.activity.login;

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
import kul.pl.biblioteka.models.LoginUserModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.activity.register.RegisterActivity;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialog;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialogListener;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract.View, NoInternetDialogListener {

    private EditText loginETex;
    private EditText passwordETex;
    private Button loginBtn;
    private TextView registrationText;
    private ProgressBar progressBar;
    private LoginActivityContract.Presenter presenter;
    private NoInternetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
        setOnClickListeners();
        dialog=new NoInternetDialog(this);
        presenter = new LoginActivityPresenter(this);
    }

    private void setOnClickListeners() {
        loginBtn.setOnClickListener(loginOnClickListener);
        registrationText.setOnClickListener(registrationOnClickListener);
    }

    private View.OnClickListener registrationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openRegisterActivity();
        }
    };

    private void openRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private View.OnClickListener loginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startProgressBar();
            presenter.onLoginClicked(new LoginUserModel(
                    loginETex.getText().toString(),
                    loginETex.getText().toString(),
                    passwordETex.getText().toString()
            ));
        }
    };

    private void initComponents() {
        loginETex = findViewById(R.id.login_nick_editText);
        passwordETex = findViewById(R.id.login_editText_password);
        loginBtn = findViewById(R.id.login_bottom_login);
        registrationText = findViewById(R.id.login_textView_register);
        progressBar = findViewById(R.id.login_progressBar);
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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void openOnInternetDialog() {
        dialog.show(getSupportFragmentManager(),getString(R.string.no_internet_dialog));
        dialog.setOnClickedBack();
    }

    @Override
    public void errorEmptyLogin() {
        loginETex.setError(getString(R.string.login_is_empty));
    }

    @Override
    public void errorEmptyPassword() {
        passwordETex.setError(getString(R.string.password_is_empty));
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.onLoginClicked(new LoginUserModel(
                        loginETex.getText().toString(),
                        loginETex.getText().toString(),
                        passwordETex.getText().toString()
                ));
                    dialog.closeDialog();
            }
        },5000);
    }

    @Override
    public void showToast() {
        Toast.makeText(this, R.string.no_internet_message, Toast.LENGTH_LONG).show();
    }
}