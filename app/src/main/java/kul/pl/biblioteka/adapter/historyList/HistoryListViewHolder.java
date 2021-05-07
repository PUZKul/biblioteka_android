package kul.pl.biblioteka.adapter.historyList;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;
import kul.pl.biblioteka.ui.activity.MainActivity;

public class HistoryListViewHolder extends RecyclerView.ViewHolder{

    private ImageView imageBook;
    private TextView titleTextView;
    private  TextView borrowedTextView;
    private  TextView returnedTextView;

    public HistoryListViewHolder(@NonNull View itemView) {
        super(itemView);
        setComponents(itemView);
    }

    private void setComponents(View view) {
        borrowedTextView=view.findViewById(R.id.history_list_textView_dateOfBorrow);
        returnedTextView=view.findViewById(R.id.history_list_textView_returned);
        titleTextView=view.findViewById(R.id.history_list_image);
        imageBook=view.findViewById(R.id.history_list_textView_title);
    }


    public void setReturnedText(String date){
        returnedTextView.setText(date);
    }

    public void setBorrowedText(String date){
        borrowedTextView.setText(date);
    }

    public void setTitleText(String title){
        titleTextView.setText(title);
    }

    public void setImageBook(Uri bookUri){
        Picasso.with(MainActivity.getAppContext()).load(bookUri).into(imageBook);;
    }
}
