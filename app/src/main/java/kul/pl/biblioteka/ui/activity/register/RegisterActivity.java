package kul.pl.biblioteka.ui.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.models.UserModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.activity.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity  implements RegisterActivityContract.View{

    private Button registrationBtn;
    private TextView loginText;
    private EditText register_editText_nick;
    private EditText register_editText_email;
    private EditText register_editText_password;
    private EditText register_editText_repeatPassword;
    private ProgressBar progressBar;
    private String nick;
    private String email;
    private String password;
    private String repeatPassword;
    private RegisterActivityContract.Presenter presenter;
    private boolean correct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();
        setOnClickListeners();
        presenter= new RegisterActivityPresenter( this);
    }

    private void setOnClickListeners() {
        registrationBtn.setOnClickListener(registrationOnClickLisner);
        loginText.setOnClickListener(loginOnClickLisner);
    }


    private View.OnClickListener loginOnClickLisner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener registrationOnClickLisner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            nick=register_editText_nick.getText().toString();
            email=register_editText_email.getText().toString();
            password=register_editText_password.getText().toString();
            repeatPassword=register_editText_repeatPassword.getText().toString();
            correct=false;

            if(TextUtils.isEmpty(nick)){
                register_editText_nick.setError("Nick cannot be empty!");
                correct=true;
            }
            if (TextUtils.isEmpty(email)) {
                register_editText_email.setError("E-mail cannot be empty!");
                correct=true;
            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                register_editText_email.setError("Incorrect E-mail address!");
                correct=true;
            }
            if (TextUtils.isEmpty(password)){
                register_editText_password.setError("Password cannot be empty!");
                correct=true;
            }
            if(TextUtils.isEmpty(repeatPassword)){
                register_editText_repeatPassword.setError("Please repeat your password!");
                correct=true;
            }else if(password!=repeatPassword){
                register_editText_repeatPassword.setError("Please enter the correct password ");
                correct=true;
            }

            presenter.onRegisterClicked(new UserModel(nick,email,password));
            if(correct=true){

                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }else
                correct=false;


        }
    };

    private void initComponents() {
        registrationBtn=findViewById(R.id.register_btn_register);
        loginText=findViewById(R.id.register_textasbatton_login);
        register_editText_nick=findViewById(R.id.register_editText_nick);
        register_editText_email=findViewById(R.id.register_editText_email);
        register_editText_password=findViewById(R.id.register_editText_password);
        register_editText_repeatPassword=findViewById(R.id.register_editText_repeatPassword);
    }
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
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
        Intent intent= new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}