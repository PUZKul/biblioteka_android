package kul.pl.biblioteka.ui.fragments.readingAndHistoryEmptyLists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;

public class EmptyReservationsFragment extends Fragment {

    public EmptyReservationsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty_reservations, container, false);
    }
}