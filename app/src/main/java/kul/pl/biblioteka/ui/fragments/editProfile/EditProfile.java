package kul.pl.biblioteka.ui.fragments.editProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.fragments.dialogs.DialogListener;
import kul.pl.biblioteka.ui.fragments.dialogs.DialogPasswordSecurity;

public class EditProfile extends Fragment implements DialogListener {

    private Button cancelBtn;
    private Button saveBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        initComponents(view);
        setOnClickListeners(view);
        return view;
    }

    private void setOnClickListeners(View view) {
        cancelBtn.setOnClickListener(cancelOnClickListener(view));
        saveBtn.setOnClickListener(saveOnClickListener(view));
    }

    private View.OnClickListener cancelOnClickListener(final View view) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileFragment(view);
            }
        };
    }

    private void openProfileFragment(View view){
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
    }

    private View.OnClickListener saveOnClickListener(final View view) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogPasswordSecurity(view);
            }
        };
    }

    private void openDialogPasswordSecurity(View view){
        DialogPasswordSecurity dialog=new DialogPasswordSecurity();
        dialog.setDialogListener(this);
        dialog.show(getActivity().getSupportFragmentManager(),"Nie wiem jakie");
    }

    private void initComponents(View view) {
        cancelBtn=view.findViewById(R.id.editProfile_btn_cancel);
        saveBtn=view.findViewById(R.id.editProfile_btn_save);
    }

    @Override
    public void applyPassword(String password) {
        //To do check password and toast
        Intent intent=new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}