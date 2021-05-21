package kul.pl.biblioteka.adapter.reservationList;

import android.net.Uri;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;
import kul.pl.biblioteka.models.ReservationBookModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.utils.Helper;

public class ReservationListRecycleViewAdapter extends RecyclerView.Adapter<ReservationListViewHolder>{

    private List<ReservationBookModel> booksLists;
    private OnItemClickListener onItemClickListener;

    public ReservationListRecycleViewAdapter(List<ReservationBookModel> booksLists,OnItemClickListener onItemClickListener) {
        this.booksLists = booksLists;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public ReservationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(MainActivity.getAppContext());
        View view =  mInflater.inflate(R.layout.item_list_reservations, parent, false);
        return new ReservationListViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationListViewHolder holder, int position) {
        holder.setImageBook(Uri.parse(booksLists.get(position).getImageUri()));
        holder.setReservationTextView(Helper.getShortDate(booksLists.get(position).getDateReservation()));
        holder.setTitleTextView(booksLists.get(position).getTitle());
        holder.setIdBook(booksLists.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return booksLists.size();
    }

}
