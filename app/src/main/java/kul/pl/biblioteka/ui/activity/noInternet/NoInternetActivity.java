package kul.pl.biblioteka.ui.activity.noInternet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.dataAccess.InternetConnection;

public class NoInternetActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_internet);
        button = findViewById(R.id.no_internet_button_back);
        button.setOnClickListener(onBackClick);
    }

    private View.OnClickListener onBackClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(InternetConnection.isConnection(getApplicationContext()))
                finish();
        }
    };
}
