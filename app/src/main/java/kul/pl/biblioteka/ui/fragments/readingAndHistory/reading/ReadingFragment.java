package kul.pl.biblioteka.ui.fragments.readingAndHistory.reading;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.adapter.VerticalSpaceItemDecoration;
import kul.pl.biblioteka.adapter.darkList.small.DarkSmallListRecycleViewAdapter;
import kul.pl.biblioteka.adapter.readingList.ReadingListRecycleViewAdapter;
import kul.pl.biblioteka.models.HistoryBookModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialog;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialogListener;
import kul.pl.biblioteka.ui.fragments.readingAndHistory.empty.EmptyReadingFragment;
import kul.pl.biblioteka.ui.fragments.readingAndHistory.empty.EmptyReservationsFragment;

public class ReadingFragment extends Fragment implements ReadingFragmentContact.View, NoInternetDialogListener {

    private RecyclerView recyclerView;
    private ReadingFragmentPresenter presenter;
    private ProgressBar progressBar;
    private NoInternetDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading, container, false);
        initComponents(view);
        dialog = new NoInternetDialog(this);
        presenter = new ReadingFragmentPresenter(this);
        presenter.setList();
        return view;
    }

    private void initComponents(View view) {
        progressBar = view.findViewById(R.id.reading_progressBar);
        recyclerView = view.findViewById(R.id.reading_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(25));
    }

    @Override
    public void setList(List<HistoryBookModel> books) {
        if (books.size() != 0) {
            recyclerView.setAdapter(new ReadingListRecycleViewAdapter(books));
        } else {
            getActivity().getSupportFragmentManager().beginTransaction().
                    add(((ViewGroup) getView().getParent()).getId(), new EmptyReadingFragment(), "Empty")
                    .addToBackStack(getView().getClass().getName())
                    .commit();
        }
    }

    @Override
    public void setDarkList() {
        recyclerView.setAdapter(new DarkSmallListRecycleViewAdapter(MainActivity.getAppContext()));
    }

    @Override
    public void setEmptyLayout() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.readingAndHistory_fragmentContainer, new EmptyReservationsFragment())
                .addToBackStack(null).commit();
    }

    @Override
    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void openOnInternetDialog() {
        dialog.show(getActivity().getSupportFragmentManager(), getString(R.string.no_internet_dialog));
        dialog.setOnClickedBack();
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.setList();
                dialog.closeDialog();
            }
        }, 5000);
    }

    @Override
    public void showNoInternetToast() {
        Toast.makeText(MainActivity.getAppContext(), R.string.no_internet_message, Toast.LENGTH_LONG).show();
    }
}