package kul.pl.biblioteka.adapter.copiesOfBookList;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;

public class CopiesOfBookListViewHolder extends RecyclerView.ViewHolder{

    private TextView bookId;
    private TextView status;
    private ImageView image;
    private OnItemClickListener onItemClickListener;
    private View view;

    public CopiesOfBookListViewHolder(@NonNull View itemView) {
        super(itemView);
        setComponents(itemView);
        view=itemView;
    }

    private void setComponents(View view) {
        bookId=view.findViewById(R.id.CopiesOfBook_text_copyNumber);
        status =view.findViewById(R.id.CopiesOfBook_text_status);
        image=view.findViewById(R.id.CopiesOfBook_image_bookmark);
    }

    public void setImage(Drawable image) {
        this.image.setImageDrawable(image);
    }

    public void setBookId(String  bookId) {
       this.bookId.setText(bookId);
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }
}
