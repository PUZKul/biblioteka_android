package kul.pl.biblioteka.ui.fragments.readingAndHistoryEmptyLists;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.fragments.home.HomeFragment;

public class EmptyReadingFragment extends Fragment {

    private ImageView HomeImage;
    private TextView AddText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty_reading, container, false);
        initComponents(view);
        setOnClickListener();
        return view;
    }
    private void initComponents(View view) {
        HomeImage=view.findViewById(R.id.imageEmptyReading_image_emptyReadingList);
        AddText=view.findViewById(R.id.emptyReadingFragment_text_addBookNow);
    }

    private void setOnClickListener(){
        HomeImage.setOnClickListener(onLoginClicked);
        AddText.setOnClickListener(onLoginClicked);
    }

    private View.OnClickListener onLoginClicked =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), HomeFragment.class);
            startActivity(intent);
        }
    };
}