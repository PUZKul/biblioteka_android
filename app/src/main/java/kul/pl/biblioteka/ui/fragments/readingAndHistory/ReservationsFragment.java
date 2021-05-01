package kul.pl.biblioteka.ui.fragments.readingAndHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;

public class ReservationsFragment extends Fragment {

    public ReservationsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty_reservations, container, false);
       // return inflater.inflate(R.layout.fragment_reservations_list_item, container, false);
    }
}