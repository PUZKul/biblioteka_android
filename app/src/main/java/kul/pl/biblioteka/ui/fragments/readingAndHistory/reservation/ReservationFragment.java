package kul.pl.biblioteka.ui.fragments.readingAndHistory.reservation;

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
import kul.pl.biblioteka.adapter.historyList.HistoryListRecycleViewAdapter;
import kul.pl.biblioteka.adapter.readingList.ReadingListRecycleViewAdapter;
import kul.pl.biblioteka.adapter.reservationList.ReservationListRecycleViewAdapter;
import kul.pl.biblioteka.models.HistoryBookModel;
import kul.pl.biblioteka.models.ReservationBookModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialog;
import kul.pl.biblioteka.ui.dialogs.noInternet.NoInternetDialogListener;
import kul.pl.biblioteka.ui.fragments.readingAndHistory.empty.EmptyReservationsFragment;

public class ReservationFragment extends Fragment implements ReservationFragmentContact.View , NoInternetDialogListener {

    private RecyclerView recyclerView;
    private ReservationFragmentPresenter presenter;
    private ProgressBar progressBar;
    private NoInternetDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);
        initComponents(view);
        dialog=new NoInternetDialog(this);
        presenter=new ReservationFragmentPresenter(this);
        presenter.setList();
        return view;
    }


    private void initComponents(View view) {
        progressBar=view.findViewById(R.id.reservation_progressBar);
        recyclerView=view.findViewById(R.id.reservation_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(25));
    }

    @Override
    public void setList(List<ReservationBookModel> books) {
        recyclerView.setAdapter(new ReservationListRecycleViewAdapter(books));
    }

    @Override
    public void setDarkList() {
        recyclerView.setAdapter(new DarkSmallListRecycleViewAdapter(MainActivity.getAppContext()));
    }

    @Override
    public void setEmptyLayout() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.readingAndHistory_fragmentContainer,new EmptyReservationsFragment())
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
        dialog.show(getActivity().getSupportFragmentManager(),"No Internet dialog");
        dialog.setOnClickedBack();
    }

    @Override
    public void goBackToTheFragment() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.setList();
                dialog.closeDialog();
            }
        },5000);
    }

    @Override
    public void showToast() {
        Toast.makeText(MainActivity.getAppContext(),"Operation unavailable. Still no internet.", Toast.LENGTH_LONG).show();
    }
}