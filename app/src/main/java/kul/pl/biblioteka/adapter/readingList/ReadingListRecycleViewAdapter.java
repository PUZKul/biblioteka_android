package kul.pl.biblioteka.adapter.readingList;

import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ReadingListViewHolder holder, int position) {
        holder.setBorrowedText(Helper.getShortDate(booksList.get(position).getDateIssued()));
        holder.setImageBook(Uri.parse(booksList.get(position).getImageUrl()));
        holder.setTitleText(booksList.get(position).getTitle());
        holder.setTimeAgoText(Helper.getDefaultDateFormat(booksList.get(position).getExpectedDate()));
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
