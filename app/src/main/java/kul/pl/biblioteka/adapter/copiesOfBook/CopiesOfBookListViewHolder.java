package kul.pl.biblioteka.adapter.copiesOfBook;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;

public class CopiesOfBookListViewHolder extends RecyclerView.ViewHolder{

    private TextView bookId;
    private TextView status;
    private OnItemClickListener onItemClickListener;
    private View view;

    public CopiesOfBookListViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
        super(itemView);
        setComponents(itemView);
        view=itemView;
        this.onItemClickListener=onItemClickListener;
    }

    private void setComponents(View view) {
        //todo change id name
        bookId=view.findViewById(R.id.list_rating);
        status =view.findViewById(R.id.list_button_more);
    }

    public void setBookId(String  bookId) {
       this.bookId.setText(bookId);
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }
}
