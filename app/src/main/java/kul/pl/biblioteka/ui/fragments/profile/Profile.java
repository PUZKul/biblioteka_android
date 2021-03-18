package kul.pl.biblioteka.ui.fragments.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.login.LoginActivity;
import kul.pl.biblioteka.ui.fragments.editProfile.EditProfile;


public class Profile extends Fragment {

    private PopupMenu menu;
    private ImageButton menuButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initComponents(view);
        setOnClickListener();
        return view;
    }

    private void initComponents(View view) {
        menuButton = view.findViewById(R.id.profile_btn_menu);
        menu = new PopupMenu(view.getContext(), menuButton);
        menu.getMenuInflater().inflate(R.menu.profile_menu, menu.getMenu());
    }

    private void setOnClickListener() {
        menuButton.setOnClickListener(menuButtonOnClickListener());
    }

    private View.OnClickListener menuButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setOnMenuItemClickListener(menuOnClickListener());
                menu.show();
            }
        };
    }

    private PopupMenu.OnMenuItemClickListener menuOnClickListener() {
        return new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.bottom_menu_edit_profile) {
                    openEditProfileFragment();
                } else {
                    openLoginActivity();
                }
                return true;
            }
        };
    }

    private void openEditProfileFragment() {
        //TO DO set fragment with rdit profile fragmnet
        getActivity().getSupportFragmentManager().beginTransaction().
                add(((ViewGroup)getView().getParent()).getId(),new EditProfile(),"Edit profile")
                .addToBackStack(getView().getClass().getName())
                .commit();
    }

    private void openLoginActivity() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        //To Do information toast
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}