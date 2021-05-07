package kul.pl.biblioteka.adapter.readingList;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.List;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.models.HistoryBookModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.utils.Helper;

public class ReadingListRecycleViewAdapter extends RecyclerView.Adapter<ReadingListViewHolder> {


    private List<HistoryBookModel> booksList;

    public ReadingListRecycleViewAdapter(List<HistoryBookModel> booksList) {
        this.booksList = booksList;
    }

    @NonNull
    @Override
    public ReadingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(MainActivity.getAppContext());
        View view = mInflater.inflate(R.layout.item_list_reading, parent, false);
        return new ReadingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadingListViewHolder holder, int position) {
        holder.setBorrowedText(Helper.getShortDate(booksList.get(position).getDateIssued()));
        holder.setImageBook(Uri.parse(booksList.get(position).getImageUrl()));
        holder.setTitleText(booksList.get(position).getTitle());
        //todo add static method(Holder class) to added 30 day
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String date = LocalDate.parse(Helper.getDefaultDateFormat(booksList.get(position).getDateIssued())).plusDays(30).toString();
            holder.setTimeAgoText(date);
        }
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
