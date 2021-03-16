package kul.pl.biblioteka.homeView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kul.pl.biblioteka.R;

public class ListHelper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list_item);
    }
}