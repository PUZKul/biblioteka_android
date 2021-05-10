package kul.pl.biblioteka.ui.fragments.notLoggedIn.historyAndReading.notLoggedInReading;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.login.LoginActivity;

public class NotLoggedInReading  extends Fragment {

    private ImageView loginImage;
    private TextView loginText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_login_reading, container, false);
        initComponents(view);
        setOnClickListener();
        return view;
    }

    private void initComponents(View view) {
        loginImage=view.findViewById(R.id.fragmentNoLoginReading_image_LoginIn);
        loginText=view.findViewById(R.id.fragmentNoLoginReading_text_LoginIn);
    }

    private void setOnClickListener(){
        loginText.setOnClickListener(onLoginClicked);
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