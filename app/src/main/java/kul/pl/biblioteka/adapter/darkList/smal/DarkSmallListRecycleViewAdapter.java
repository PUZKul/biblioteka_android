package kul.pl.biblioteka.adapter.darkList.smal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.recommendedList.RecommendedListViewHolder;

public class DarkSmallListRecycleViewAdapter extends RecyclerView.Adapter<DarkSmallListViewHolder>{

    private Context context;

    public DarkSmallListRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DarkSmallListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view =  mInflater.inflate(R.layout.item_list_small_dark, parent, false);
        return new DarkSmallListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DarkSmallListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
