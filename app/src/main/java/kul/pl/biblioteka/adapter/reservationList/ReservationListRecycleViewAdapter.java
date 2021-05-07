package kul.pl.biblioteka.adapter.reservationList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.OnItemClickListener;
import kul.pl.biblioteka.ui.activity.MainActivity;

public class ReservationListRecycleViewAdapter extends RecyclerView.Adapter<ReservationListViewHolder> implements OnItemClickListener{


    @NonNull
    @Override
    public ReservationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(MainActivity.getAppContext());
        View view =  mInflater.inflate(R.layout.item_list_reservations, parent, false);
        return new ReservationListViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationListViewHolder holder, int position) {
        //todo set components
    }

    @Override
    public int getItemCount() {
        //todo return book list size
        return 0;
    }

    @Override
    public void onClick(int idBook) {
        //todo cancel reservation current book
    }
}
