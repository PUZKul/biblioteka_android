package kul.pl.biblioteka.ui.dialogs.copiesOfBooks;

import android.os.Handler;

import java.util.List;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.CopiesOfBookModel;
import kul.pl.biblioteka.models.UserModel;
import kul.pl.biblioteka.ui.activity.MainActivity;

public class CopiesOfBooksDialogPresenter extends APIAdapter implements CopiesOfBooksDialogContract.Presenter {

    private CopiesOfBooksDialogContract.View view;
    private LibraryAccess api;
    private long idBook;

    public CopiesOfBooksDialogPresenter(CopiesOfBooksDialogContract.View view, int idBook) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
        api.getCopiesOfBook(idBook);
    }

    @Override
    public void onCopiesOfBookReceive(List<CopiesOfBookModel> book) {
        view.setList(book);
    }

    @Override
    public void reserveBook(long idBook) {
        this.idBook = idBook;
        if (idBook != 0) {
            if (InternetConnection.isConnection(MainActivity.getAppContext())) {
                view.startProgressBar();
                api.getUserDetails(LocalDataAccess.getToken());
            } else {
                openNoInternetDialog();
            }
        }
    }

    @Override
    public void onUserDetailsReceive(UserModel user) {
        if (user.getPhone() == null) {
            view.openInformDialog();
            view.endProgressBar();
        } else {
            view.startProgressBar();
            if (InternetConnection.isConnection(MainActivity.getAppContext())) {
                view.startProgressBar();
                api.reserveBook(LocalDataAccess.getToken(), idBook);
            } else {
                openNoInternetDialog();
            }
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
    public void onReserveBook() {
        view.endProgressBar();
        view.showSuccessReservationBookToast();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.endProgressBar();
        if (error.getStatus() == 403) {
            view.showStopBorrowDialog();
        }
    }
}