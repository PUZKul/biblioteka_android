package kul.pl.biblioteka.ui.dialogs.continueRegsistration;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.dialogs.copiesOfBooks.CopiesOfBooksDialogPresenter;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialog;

public class ContinueRegistrationDialog  extends AppCompatDialogFragment  {
    
    private Button continueRegistration;
    private Button goTo;
    private ContinueRegistrationDialogListener listener;

    public ContinueRegistrationDialog(ContinueRegistrationDialogListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_copies_of_books, null);
        initComponents(view);
        setOnClickListener();
        builder.setView(view);
        return builder.create();
    }

    private void initComponents(View view) {
        continueRegistration=view.findViewById(R.id.dialog_conitinue_registration_comlpete);
        goTo=view.findViewById(R.id.dialog_conitinue_registration_go_to);
    }

    private void setOnClickListener(){
        continueRegistration.setOnClickListener(onContinueClicked);
        goTo.setOnClickListener(onToGoBooksListClicked);
    }

    private View.OnClickListener onContinueClicked=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onContinueClicked();
        }
    };

    private View.OnClickListener onToGoBooksListClicked=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onGoToListBooksClicked();
        }
    };
}