package kul.pl.biblioteka.adapter.readingList;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;
import kul.pl.biblioteka.ui.activity.MainActivity;

public class ReadingListViewHolder extends RecyclerView.ViewHolder{

    private ImageView imageBook;
    private TextView titleTextView;
    private RatingBar ratingBar;
    private  TextView borrowedTextView;
    private TextView timeAgoTextView;

    public ReadingListViewHolder(@NonNull View itemView) {
        super(itemView);
        setComponents(itemView);
    }


    private void setComponents(View view) {
        imageBook=view.findViewById(R.id.reading_item_list_textView_title);
        titleTextView=view.findViewById(R.id.reading_item_list_image);
        borrowedTextView=view.findViewById(R.id.reading_item_list_textView_dateOfBorrow);
        timeAgoTextView=view.findViewById(R.id.reading_item_list_textView_timeAgo);
    }

    public void setTimeAgoText(String timeAgoText){
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
        Picasso.with(MainActivity.getAppContext()).load(bookUri).into(imageBook);;
    }

}
