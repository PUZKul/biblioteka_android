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
import kul.pl.biblioteka.utils.Helper;

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
        View view =  mInflater.inflate(R.layout.item_list_copies_of_books, parent, false);
        return new CopiesOfBookListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CopiesOfBookListViewHolder holder, int position) {
            setBookDetails(holder,position);
    }

    private void setBookDetails(CopiesOfBookListViewHolder holder, int position) {
        holder.setBookId("#"+booksList.get(position).getId());
        if(!booksList.get(position).isBorrow()){
            if(booksList.get(position).isAccess()){
                holder.setStatus("Available");
                holder.setBalloon("Book is available to borrow and read at home");
            } else{
                holder.setStatus("Restricted access");
                holder.setBalloon("Book is available to borrow but it can be read only inside of the library");
            }
        }else {
            holder.setImage(R.drawable.bookmark_yellow);
            holder.setStatus("Occupied ");
            holder.setBalloon("Available from "+Helper.getShortDate(booksList.get(position).getApproximateDate()));
        }

    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
