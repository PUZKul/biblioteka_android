package kul.pl.biblioteka.adapter.copiesOfBookList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.models.CopiesOfBookModel;

public class CopiesOfBookListRecycleViewAdapter extends RecyclerView.Adapter<CopiesOfBookListViewHolder> {

    private Context context;
    private List<CopiesOfBookModel> booksList;

    public CopiesOfBookListRecycleViewAdapter(Context context, List<CopiesOfBookModel> booksList) {
        this.context = context;
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public CopiesOfBookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view =  mInflater.inflate(R.layout.copies_of_books_item, parent, false);
        return new CopiesOfBookListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CopiesOfBookListViewHolder holder, int position) {
            setBookDetails(holder,position);
    }

    private void setBookDetails(CopiesOfBookListViewHolder holder, int position) {
        holder.setBookId("#"+booksList.get(position).getId());
        if(booksList.get(position).isAccess()){
            holder.setStatus("Available");
        } else{
            holder.setStatus("Restricted access");
        }
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
