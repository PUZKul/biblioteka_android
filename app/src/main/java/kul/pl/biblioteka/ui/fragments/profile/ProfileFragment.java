package kul.pl.biblioteka.ui.fragments.profile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialog;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialogListener;
import kul.pl.biblioteka.ui.fragments.editProfile.EditProfile;


public class ProfileFragment extends Fragment implements ProfileFragmentContact.view, NoInternetDialogListener {

    private ProfileFragmentPresenter presenter;
    private PopupMenu menu;
    private ImageButton menuButton;
    private TextView nickText;
    private TextView readBooksText;
    private TextView currentBooksText;
    private TextView firstName;
    private TextView lastName;
    private TextView phone;
    private TextView address;
    private ProgressBar progressBar;
    private NoInternetDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initComponents(view);
        setOnClickListener();
        dialog=new NoInternetDialog(this);
        presenter=new ProfileFragmentPresenter(this);
        presenter.setUserDetails();
        return view;
    }

    private void initComponents(View view) {
        menuButton = view.findViewById(R.id.profile_btn_menu);
        menu = new PopupMenu(view.getContext(), menuButton);
        menu.getMenuInflater().inflate(R.menu.profile_menu, menu.getMenu());
        nickText= view.findViewById(R.id.profile_textView_nick);
        readBooksText=view.findViewById(R.id.profile_textView_readBooks);
        currentBooksText=view.findViewById(R.id.profile_textView_currentBooks);
        progressBar=view.findViewById(R.id.progressBar);
        firstName=view.findViewById(R.id.profile_firstName_textView);
        lastName=view.findViewById(R.id.profile_lastName_textView);
        phone=view.findViewById(R.id.profile_phone_textView);
        address=view.findViewById(R.id.profile_address_textView);
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
    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    @Override
    public void setAddress(String address) {
        this.address.setText(address);
    }

    @Override
    public void setPhone(String phone) {
        this.phone.setText(phone);
    }


    @Override
    public void openOnInternetDialog() {
        dialog.show(getActivity().getSupportFragmentManager(),"No Internet dialog");
        dialog.setOnClickedBack();
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.setUserDetails();
                dialog.closeDialog();
            }
        },5000);
    }

    @Override
    public void showToast() {
        Toast.makeText(MainActivity.getAppContext(),"Operation unavailable. Still no internet.", Toast.LENGTH_LONG).show();
    }
}