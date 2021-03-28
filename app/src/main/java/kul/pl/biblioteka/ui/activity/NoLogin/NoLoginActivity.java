package kul.pl.biblioteka.ui.activity.NoLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.login.LoginActivity;
import kul.pl.biblioteka.ui.activity.register.RegisterActivity;
import kul.pl.biblioteka.ui.fragments.home.HomeFragment;

public class NoLoginActivity extends AppCompatActivity {
    private TextView login;
    private TextView register;
    private Button goToTheLibrary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nologin);
        initComponents();

    }

    private void setOnClickListener(){
        login.setOnClickListener(loginOnClickListener);
        register.setOnClickListener(registerOnClickListener);
        goToTheLibrary.setOnClickListener(goToTheLibraryListener);
    }
    private View.OnClickListener registerOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(NoLoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
    };

      private View.OnClickListener loginOnClickListener= new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent= new Intent(NoLoginActivity.this, LoginActivity.class);
            startActivity(intent);
          }
      };

      private View.OnClickListener goToTheLibraryListener= new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent=new Intent(NoLoginActivity.this, HomeFragment.class);// tutaj w razie czego trzeb azmienic klase
            startActivity(intent);
          }
      };


  private void initComponents(){
        login=findViewById(R.id.welcome_btn_login);
        register=findViewById(R.id.welcome_btn_register);
        goToTheLibrary=findViewById(R.id.welcome_button_GoToTheLibrary);
  }

   }



