package kul.pl.biblioteka.adapter.reservationList;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;

public class ReservationListViewHolder extends RecyclerView.ViewHolder{

    private ImageView imageBook;
    private TextView titleTextView;
    private  TextView reservationTextView;
    private TextView timeAgoTextView;
    private Button cancel;
    private OnItemClickListener onItemClickListener;

    public ReservationListViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener=onItemClickListener;
        initComponents(itemView);
        setOnCLickListeners();
    }

    private void initComponents(View itemView) {
        imageBook=imageBook.findViewById(R.id.reservation_item_list_image);
        titleTextView=itemView.findViewById(R.id.reservation_item_list_text_title);
        reservationTextView=itemView.findViewById(R.id.reservation_item_list_text_dateOfBorrow);
        timeAgoTextView=itemView.findViewById(R.id.reservation_item_list_text_timeAgo);
        cancel=itemView.findViewById(R.id.reservation_item_list_button_Cancel);
    }

    private  void setOnCLickListeners(){
        cancel.setOnClickListener(onCancelClicked);
    }

    private  View.OnClickListener onCancelClicked=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //todo cancel reservation current book
            // onItemClickListener.onClick();
        }
    };
}
