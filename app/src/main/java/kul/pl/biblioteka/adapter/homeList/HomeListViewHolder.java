package kul.pl.biblioteka.adapter.homeList;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;

public class HomeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView imageBook;
    private TextView titleTextView;
    private TextView authorTextView;
    private TextView publisherTextView;
    private Button moreButton;
    private RatingBar ratingBar;
    private OnItemClickListener onItemClickListener;

    public HomeListViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);
        setComponents(itemView);
        this.onItemClickListener=onItemClickListener;
    }


    private void setComponents(View view) {
        ratingBar=view.findViewById(R.id.ratingBar);
        moreButton =view.findViewById(R.id.list_button_more);
        publisherTextView=view.findViewById(R.id.list_text_publisher);
        authorTextView=view.findViewById(R.id.list_text_author);
        titleTextView=view.findViewById(R.id.list_text_title);
        imageBook=view.findViewById(R.id.list_image);
    }

    public void setRatingBar(float rating){
        ratingBar.setRating(rating);
    }

    public void setPublisherText(String  publisher){
        publisherTextView.setText(publisher);
    }

    public void  setAuthorText(String author){
        authorTextView.setText(author);
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
