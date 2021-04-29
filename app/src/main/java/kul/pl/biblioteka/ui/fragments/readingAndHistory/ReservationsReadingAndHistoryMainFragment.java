package kul.pl.biblioteka.ui.fragments.readingAndHistory;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kul.pl.biblioteka.R;

public class ReservationsReadingAndHistoryMainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservations_reading_and_history, container, false);
    }
}