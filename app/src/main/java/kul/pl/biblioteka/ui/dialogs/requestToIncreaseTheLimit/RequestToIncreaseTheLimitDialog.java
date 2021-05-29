package kul.pl.biblioteka.ui.dialogs.requestToIncreaseTheLimit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.ui.activity.MainActivity;

public class RequestToIncreaseTheLimitDialog extends AppCompatDialogFragment {

    private EditText decryption;
    private Button send;
    private Button back;

    private RequestToIncreaseTheLimitListener listener;

    public RequestToIncreaseTheLimitDialog(RequestToIncreaseTheLimitListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_request_to_increase_limit, null);
        initComponents(view);
        setOnClickListeners();
        builder.setView(view);
        return builder.create();
    }

    private void initComponents(View view) {
        send=view.findViewById(R.id.request_to_increase__button_send);
        back=view.findViewById(R.id.request_to_increase_cancel);
        decryption=view.findViewById(R.id.request_to_increase_edit_text_descrytpion);
    }

    private void setOnClickListeners(){
        send.setOnClickListener(onSendListener);
        back.setOnClickListener(onBackListener);
    }

    private View.OnClickListener onBackListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private View.OnClickListener onSendListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(decryption.getText().toString().isEmpty()){
                showToast();
            }else {
                dismiss();
                listener.sendDecryption(decryption.getText().toString());
            }
        }
    };

    private void showToast(){
        Toast.makeText(MainActivity.getAppContext(),"Description is empty. Enter the reason.",Toast.LENGTH_LONG).show();
    }
}
