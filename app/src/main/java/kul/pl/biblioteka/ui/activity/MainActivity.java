package kul.pl.biblioteka.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.fragments.home.HomeFragment;
import kul.pl.biblioteka.ui.fragments.notLoggedIn.hiatoryAndReading.NotLoggedInReadingAndHistory;
import kul.pl.biblioteka.ui.fragments.notLoggedIn.profile.NotLoggedInProfileFragment;
import kul.pl.biblioteka.ui.fragments.profile.Profile;
import kul.pl.biblioteka.ui.fragments.readingAndHistory.ReadingAndHistory_Main;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottom_nav;
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();
        setContentView(R.layout.activity_bottom_navigation);
        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.bottom_nav_profile:
                           // selectedFragment = new Profile();
                            selectedFragment = new NotLoggedInProfileFragment();
                            break;
                        case R.id.bottom_nav_book:
                            selectedFragment = new NotLoggedInReadingAndHistory();
                            break;
                        case R.id.bottom_nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
                    return true;
                }
            };
}