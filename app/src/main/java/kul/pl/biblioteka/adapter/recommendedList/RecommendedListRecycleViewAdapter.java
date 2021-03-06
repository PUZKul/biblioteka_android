package kul.pl.biblioteka.adapter.recommendedList;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;
import kul.pl.biblioteka.models.BookModel;

public class RecommendedListRecycleViewAdapter extends RecyclerView.Adapter<RecommendedListViewHolder> {

    private Context context;
    private List<BookModel> booksList;
    private OnItemClickListener onItemClickListener;

    public RecommendedListRecycleViewAdapter(Context context, List<BookModel> booksList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.booksList = booksList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecommendedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.item_list_recommended_book, parent, false);
        return new RecommendedListViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedListViewHolder holder, int position) {
        setBookDetails(holder, position);
    }

    private void setBookDetails(RecommendedListViewHolder holder, int position) {
        holder.setImageBook(Uri.parse(booksList.get(position).getImageUrl()));
        holder.setAuthorText(booksList.get(position).getAuthors());
        holder.setTitleText(booksList.get(position).getTitle());
        holder.setRatingBar((float) booksList.get(position).getRating());
        holder.setIdBook(booksList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
