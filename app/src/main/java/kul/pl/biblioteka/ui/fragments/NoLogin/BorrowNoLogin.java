package kul.pl.biblioteka.ui.fragments.NoLogin;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import kul.pl.biblioteka.R;

public class BorrowNoLogin extends AppCompatActivity {
    private Button login;
    private Button register;
    private Button back;




    private void initComponents(){
        login=findViewById(R.id.no_internet_button_login);
        register=findViewById(R.id.no_internet_button_register);
        back=findViewById(R.id.no_internet_button_back);
    }
}

