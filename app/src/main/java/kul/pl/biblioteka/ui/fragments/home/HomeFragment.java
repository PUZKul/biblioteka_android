package kul.pl.biblioteka.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;

public class HomeFragment extends Fragment {

    private ImageButton sortBtn;
    private PopupMenu menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponents(view);
        setOnClickListener();
        return view;
    }

    private void setOnClickListener() {
        sortBtn.setOnClickListener(sortButtonOnClick);
    }

    private View.OnClickListener sortButtonOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menu.setOnMenuItemClickListener(menuOnClickListener);
            menu.show();
        }
    };

    private PopupMenu.OnMenuItemClickListener menuOnClickListener =
            new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    //To Do set lists
                    return true;
                }
            };

    private void initComponents(View view) {
        sortBtn = view.findViewById(R.id.home_btn_sort);
        menu = new PopupMenu(view.getContext(), sortBtn);
        menu.getMenuInflater().inflate(R.menu.sort_list_menu, menu.getMenu());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}