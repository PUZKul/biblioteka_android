package kul.pl.biblioteka.adapter.readingList;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;

public class ReadingListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView imageBook;
    private TextView titleTextView;
    private RatingBar ratingBar;
    private  TextView borrowedTextView;
    private TextView timeAgoTextView;
    private OnItemClickListener onItemClickListener;

    public ReadingListViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);
        setComponents(itemView);
        this.onItemClickListener=onItemClickListener;
    }

    private void setComponents(View view) {
        imageBook=view.findViewById(R.id.reading_item_list_textView_title);
        titleTextView=view.findViewById(R.id.reading_item_list_image);
        borrowedTextView=view.findViewById(R.id.reading_item_list_textView_dateOfBorrow);
        timeAgoTextView=view.findViewById(R.id.reading_item_list_textView_timeAgo);
    }

    private void setTimeAgoText(String timeAgoText){
        timeAgoTextView.setText(timeAgoText);
    }

    public void setBorrowedText(String date){
        borrowedTextView.setText(date);
    }

    public void setRatingBar(float rating){
        ratingBar.setRating(rating);
    }

    public void setTitleText(String title){
        titleTextView.setText(title);
    }

    public void setImageBook(Uri bookUri){
        imageBook.setImageURI(bookUri);
    }

    @Override
    public void onClick(View v) {
        //onItemClickListener.onClick();
    }
}
