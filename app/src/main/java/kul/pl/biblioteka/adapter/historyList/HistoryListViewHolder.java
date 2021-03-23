package kul.pl.biblioteka.adapter.historyList;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;

public class HistoryListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView imageBook;
    private TextView titleTextView;
    private  TextView borrowedTextView;
    private  TextView returnedTextView;

    private OnItemClickListener onItemClickListener;

    public HistoryListViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);
        setComponents(itemView);
        this.onItemClickListener=onItemClickListener;
    }

    private void setComponents(View view) {
        borrowedTextView=view.findViewById(R.id.FragmentHistory_text_dateOfBorrow);
        returnedTextView=view.findViewById(R.id.FragmentHistory_text_Returned);
        titleTextView=view.findViewById(R.id.FragmentHistory_image);
        imageBook=view.findViewById(R.id.FragmentHistory_text_title);
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
        imageBook.setImageURI(bookUri);
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onClick();
    }
}
