package kul.pl.biblioteka.ui.fragments.readingAndHistory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kul.pl.biblioteka.R;

public class ReadingFragment extends Fragment {

    public ReadingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_empty_reading, container, false);
        //return inflater.inflate(R.layout.fragment_reading_list_item, container, false);
    }
}