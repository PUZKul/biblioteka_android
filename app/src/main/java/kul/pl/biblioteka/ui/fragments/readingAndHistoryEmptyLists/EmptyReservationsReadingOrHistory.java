package kul.pl.biblioteka.ui.fragments.readingAndHistoryEmptyLists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.util.Objects;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.fragments.notLoggedIn.historyAndReading.notLoggedInHistory.NotLoggedInHistory;
import kul.pl.biblioteka.ui.fragments.notLoggedIn.historyAndReading.notLoggedInReading.NotLoggedInReading;

public class EmptyReservationsReadingOrHistory extends Fragment {

    private Button readingFragmentButton;
    private Button historyFragmentButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty_reservations_reading_or_history, container, false);
        initComponents(view);
        setOnClickListener();
        setReadingFragment();
        return view;
    }

    private void initComponents(View view) {
        readingFragmentButton=view.findViewById(R.id.EmptyReadingOrHistory_button_reading);
        historyFragmentButton=view.findViewById(R.id.EmptyReadingOrHistory_button_history);
    }

    private void  setOnClickListener(){
        readingFragmentButton.setOnClickListener(onReadingClickedListener);
        historyFragmentButton.setOnClickListener(onHistoryClickedListener);
    }

    private final View.OnClickListener onReadingClickedListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setReadingFragment();
        }
    };

    private final View.OnClickListener onHistoryClickedListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setHistoryFragment();
        }
    };

    private void setReadingFragment(){
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.EmptyReadingOrHistory_fragmentContainer,new NotLoggedInReading())
                .addToBackStack(null).commit();
    }

    private void setHistoryFragment(){
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.EmptyReadingOrHistory_fragmentContainer,new NotLoggedInHistory())
                .addToBackStack(null).commit();
    }
}