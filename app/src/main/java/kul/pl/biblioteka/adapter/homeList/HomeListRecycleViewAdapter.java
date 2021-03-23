package kul.pl.biblioteka.adapter.homeList;

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

public class HomeListRecycleViewAdapter extends RecyclerView.Adapter<HomeListViewHolder> {

    private Context context;
    private List<BookModel> booksList;
    private OnItemClickListener onItemClickListener;

    public HomeListRecycleViewAdapter(Context context, List<BookModel> booksList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.booksList = booksList;
        this.onItemClickListener= onItemClickListener;
    }

    @NonNull
    @Override
    public HomeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view =  mInflater.inflate(R.layout.book_list_item, parent, false);
        return new HomeListViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListViewHolder holder, int position) {
            setBookDetails(holder,position);
    }

    private void setBookDetails(HomeListViewHolder holder, int position) {
        holder.setImageBook(Uri.parse(booksList.get(position).getImageUrl()));
        holder.setAuthorText(booksList.get(position).getAuthors());
        holder.setTitleText(booksList.get(position).getTitle());
        holder.setPublisherText(booksList.get(position).getPublisher());
        holder.setRatingBar((float) booksList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
