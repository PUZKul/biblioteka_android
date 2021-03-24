package kul.pl.biblioteka.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.activity.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText loginETex;
    private EditText passwordETex;
    private Button loginBtn;
    private TextView registrationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        loginBtn.setOnClickListener(loginOnClickLisner);
        registrationText.setOnClickListener(registrationOnClickLisner);
    }

    private View.OnClickListener registrationOnClickLisner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener loginOnClickLisner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };

    private void initComponents(){
        loginETex=findViewById(R.id.login_nick_editText);
        passwordETex=findViewById(R.id.login_edittext_password);
        loginBtn=findViewById(R.id.login_btn_login);
        registrationText=findViewById(R.id.login_register_textViev);
    }
}