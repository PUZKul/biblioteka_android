package kul.pl.biblioteka.ui.fragments.notLoggedIn.hiatoryAndReading;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.fragments.notLoggedIn.hiatoryAndReading.notLoggedInHistory.NotLoggedInHistory;
import kul.pl.biblioteka.ui.fragments.notLoggedIn.hiatoryAndReading.notLoggedInReading.NotLoggedInReading;

public class NotLoggedInReservationsReadingAndHistory extends Fragment {

    private Button readingFragmentBtn;
    private Button historyFragmentBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_log_reservations_reading_and_history__main, container, false);
        initComponents(view);
        setOnClickListener();
        setReadingFragment();
        return view;
    }

    private void initComponents(View view) {
        readingFragmentBtn=view.findViewById(R.id.NoLoginReadingAndHistory_button_reading);
        historyFragmentBtn=view.findViewById(R.id.NoLoginReadingAndHistory_button_history);
    }

    private void  setOnClickListener(){
        readingFragmentBtn.setOnClickListener(onReadingClickedListener);
        historyFragmentBtn.setOnClickListener(onHistoryClickedListener);
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
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notLogReadingAndHistory_fragmentContainer,new NotLoggedInReading())
                .addToBackStack(null).commit();
    }

    private void setHistoryFragment(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notLogReadingAndHistory_fragmentContainer,new NotLoggedInHistory())
                .addToBackStack(null).commit();
    }
}
