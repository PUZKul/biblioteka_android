package kul.pl.biblioteka.ui.fragments.notLoggedIn.historyAndReading.notLoggedInHistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.login.LoginActivity;

public class NotLoggedInHistory extends Fragment {
    private ImageView loginImage;
    private TextView loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_login_history, container, false);
        initComponents(view);
        setOnClickListener();
        return view;
    }

    private void initComponents(View view) {
        loginImage=view.findViewById(R.id.fragmentNoLoginHistory_image_LoginIn);
        loginButton=view.findViewById(R.id.fragment_noLogin_button_Login);
    }

    private void setOnClickListener(){
        loginButton.setOnClickListener(onLoginClicked);
        loginImage.setOnClickListener(onLoginClicked);
    }

    private View.OnClickListener onLoginClicked =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    };
}
