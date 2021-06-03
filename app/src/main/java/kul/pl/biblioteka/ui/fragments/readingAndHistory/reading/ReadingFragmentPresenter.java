package kul.pl.biblioteka.ui.fragments.readingAndHistory.reading;

import android.os.Handler;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.HistoryBookModel;
import kul.pl.biblioteka.ui.activity.main.MainActivity;
import kul.pl.biblioteka.utils.PageHolder;

public class ReadingFragmentPresenter extends APIAdapter implements ReadingFragmentContact.Presenter {

    private ReadingFragmentContact.View view;
    private LibraryAccess api;

    public ReadingFragmentPresenter(ReadingFragmentContact.View view) {
        this.view = view;
        this.api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void setList() {
        view.setDarkList();
        setReadingBookList();
    }

    @Override
    public void onExtendBookRentalClicked(int idBook) {
        api.getExternalBooksRental(LocalDataAccess.getToken(), idBook);
    }

    private void setReadingBookList() {
        view.startProgressBar();
        if (InternetConnection.isConnection(MainActivity.getAppContext())) {
            api.getCurrentBooks(10, 0, LocalDataAccess.getToken());
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
    public void onCurrentBooksReceive(PageHolder<HistoryBookModel> books) {
        if (books.getContent().size() != 0)
            view.setList(books.getContent());
        else
            view.setEmptyLayout();
        view.endProgressBar();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.endProgressBar();
        if (error.getStatus() == 409)
            view.showFailureExtendBookRentalMessage();
    }

    @Override
    public void onExtendBookRentalReceive() {
        view.showSuccessExtendBookRentalMessage();
        view.endProgressBar();
        setReadingBookList();
    }

    @Override
    public void onRefreshServer() {
        view.onRefresh();
    }
}
