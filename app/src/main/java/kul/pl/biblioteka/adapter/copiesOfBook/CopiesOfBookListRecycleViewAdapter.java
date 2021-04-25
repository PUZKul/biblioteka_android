package kul.pl.biblioteka.adapter.copiesOfBook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;
import kul.pl.biblioteka.models.BookModel;

public class CopiesOfBookListRecycleViewAdapter extends RecyclerView.Adapter<CopiesOfBookListViewHolder> {

    private Context context;
    private List<BookModel> booksList;
    private OnItemClickListener onItemClickListener;

    public CopiesOfBookListRecycleViewAdapter(Context context, List<BookModel> booksList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.booksList = booksList;
        this.onItemClickListener= onItemClickListener;
    }

    @NonNull
    @Override
    public CopiesOfBookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view =  mInflater.inflate(R.layout.book_list_item, parent, false);
        return new CopiesOfBookListViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CopiesOfBookListViewHolder holder, int position) {
            setBookDetails(holder,position);
    }

    private void setBookDetails(CopiesOfBookListViewHolder holder, int position) {
        holder.setBookId("");
        holder.setStatus("");
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
