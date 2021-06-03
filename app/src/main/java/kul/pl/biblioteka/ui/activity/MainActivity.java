package kul.pl.biblioteka.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.ui.dialogs.banedUser.BanedUserDialog;
import kul.pl.biblioteka.ui.fragments.firstWindow.FirstWindowFragment;
import kul.pl.biblioteka.ui.fragments.notLoggedIn.historyAndReading.NotLoggedInReservationsReadingAndHistory;
import kul.pl.biblioteka.ui.fragments.notLoggedIn.profile.NotLoggedInProfileFragment;
import kul.pl.biblioteka.ui.fragments.profile.ProfileFragment;
import kul.pl.biblioteka.ui.fragments.readingAndHistory.ReservationsReadingAndHistoryMainFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottom_nav;
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();
        setContentView(R.layout.activity_bottom_navigation);
        bottom_nav = findViewById(R.id.mainActivity_bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().add(R.id.mainActivity_fragment_container, new FirstWindowFragment()).commit();
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.bottom_nav_profile:
                            if (LocalDataAccess.isLogin())
                                selectedFragment = new ProfileFragment();
                            else
                                selectedFragment = new NotLoggedInProfileFragment();
                            break;
                        case R.id.bottom_nav_book:
                            if (LocalDataAccess.isLogin())
                                selectedFragment = new ReservationsReadingAndHistoryMainFragment();
                            else
                                selectedFragment = new NotLoggedInReservationsReadingAndHistory();
                            break;
                        case R.id.bottom_nav_home:
                            selectedFragment = new FirstWindowFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity_fragment_container, selectedFragment).addToBackStack(null).commit();
                    return true;
                }
            };
}