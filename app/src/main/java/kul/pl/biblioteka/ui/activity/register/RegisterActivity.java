package kul.pl.biblioteka.ui.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.activity.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private Button registrationBtn;
    private TextView loginText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        registrationBtn.setOnClickListener(registrationOnClickLisner);
        loginText.setOnClickListener(loginOnClickLisner);
    }

    private View.OnClickListener registrationOnClickLisner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener loginOnClickLisner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };

    private void initComponents() {
        registrationBtn=findViewById(R.id.register_btn_register);
        loginText=findViewById(R.id.register_textasbatton_login);
    }
}