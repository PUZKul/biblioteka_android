package kul.pl.biblioteka.ui.fragments.readingAndHistory.reservation;

import android.os.Handler;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.HistoryBookModel;
import kul.pl.biblioteka.models.ReservationBookModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.utils.PageHolder;

public class ReservationFragmentPresenter extends APIAdapter implements ReservationFragmentContact.Presenter {

    private ReservationFragmentContact.View view;
    private LibraryAccess api;
    private int idBook;

    public ReservationFragmentPresenter(ReservationFragmentContact.View view) {
        this.view = view;
        this.api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void setList() {
        view.setDarkList();
        setHistoryBookList();
    }

    @Override
    public void onCancelClicked(int idBook) {
        this.idBook = idBook;
        view.openCancelReservationDialog();
    }

    @Override
    public void cancelBook() {
        view.startProgressBar();
        if (InternetConnection.isConnection(MainActivity.getAppContext())) {
            api.cancelReservation(LocalDataAccess.getToken(), idBook);
        } else {
            openNoInternetDialog();
        }
    }

    private void setHistoryBookList() {
        view.startProgressBar();
        if (InternetConnection.isConnection(MainActivity.getAppContext())) {
            api.getReservationBooks(10, 0, LocalDataAccess.getToken());
        } else {
            openNoInternetDialog();
        }
    }

    private void openNoInternetDialog() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.openOnInternetDialog();
            }
        }, 5000);
    }

    @Override
    public void onCancelReservationReceive() {
        view.endProgressBar();
        view.onSuccessCancelBookMessage();
        setList();
    }

    @Override
    public void onReservationBooksReceive(PageHolder<ReservationBookModel> books) {
        view.setList(books.getContent());
        view.endProgressBar();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        System.out.println(error.getMessage());
        System.out.println(error.getStatus());
    }
}
