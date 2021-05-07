package kul.pl.biblioteka.ui.fragments.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.fragments.editProfile.EditProfile;


public class ProfileFragment extends Fragment implements ProfileFragmentContact.view {
    private ProfileFragmentPresenter presenter;


    private PopupMenu menu;
    private ImageButton menuButton;
    private TextView nickText;
    private TextView readBooksText;
    private TextView currentBooksText;
    private TextView currentLevelText;
    private TextView nextLevelText;
    private ProgressBar experienceProgress;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initComponents(view);
        setOnClickListener();
        presenter=new ProfileFragmentPresenter(this);
        presenter.setUSeaDetails();
        return view;
    }

    private void initComponents(View view) {
        menuButton = view.findViewById(R.id.profile_btn_menu);
        menu = new PopupMenu(view.getContext(), menuButton);
        menu.getMenuInflater().inflate(R.menu.profile_menu, menu.getMenu());
        nickText= view.findViewById(R.id.profile_textView_nick);
        readBooksText=view.findViewById(R.id.profile_textView_readBooks);
        currentBooksText=view.findViewById(R.id.profile_textView_currentBooks);
        currentLevelText=view.findViewById(R.id.profile_textView_currentLvl);
        nextLevelText=view.findViewById(R.id.profile_textView_nextLvl);
        experienceProgress=view.findViewById(R.id.profile_progressBar_exp);
        progressBar=view.findViewById(R.id.progressBar);
    }

    private void setOnClickListener() {
        menuButton.setOnClickListener(menuButtonOnClickListener());
    }

    private View.OnClickListener menuButtonOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setOnMenuItemClickListener(menuOnClickListener());
                menu.show();
            }
        };
    }

    private PopupMenu.OnMenuItemClickListener menuOnClickListener() {
        return new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.bottom_menu_edit_profile) {
                    openEditProfileFragment();
                } else {
                    presenter.logoutUser();
                }
                return true;
            }
        };
    }

    private void openEditProfileFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().
                add(((ViewGroup)getView().getParent()).getId(),new EditProfile(),"Edit profile")
                .addToBackStack(getView().getClass().getName())
                .commit();
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        //todo toast information
        startActivity(intent);
    }

    @Override
    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setNick(String nick) {
      nickText.setText(nick);
    }

    @Override
    public void setReadBooks(String readBooks) {
    readBooksText.setText(readBooks);
    }

    @Override
    public void setCurrentBooks(String currentBooks) {
    currentBooksText.setText(currentBooks);
    }

    @Override
    public void setCurrentLevel(String level) {
    currentLevelText.setText(level);
    }

    @Override
    public void setNextLevel(String level) {
    nextLevelText.setText(level);
    }

    @Override
    public void setExperience(int experience) {
        experienceProgress.setProgress(experience);
    }
}