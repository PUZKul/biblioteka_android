package kul.pl.biblioteka.ui.fragments.notLoggedIn.hiatoryAndReading.notLoggedInReservations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;

public class NotLoggedInReservations extends Fragment {

    public NotLoggedInReservations() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_login_reservations, container, false);
    }
}