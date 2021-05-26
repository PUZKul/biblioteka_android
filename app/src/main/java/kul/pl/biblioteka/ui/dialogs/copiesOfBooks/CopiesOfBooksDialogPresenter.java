package kul.pl.biblioteka.ui.dialogs.copiesOfBooks;

import android.os.Handler;

import java.util.List;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.CopiesOfBookModel;
import kul.pl.biblioteka.ui.activity.MainActivity;

public class CopiesOfBooksDialogPresenter extends APIAdapter implements CopiesOfBooksDialogContract.Presenter {

    private CopiesOfBooksDialogContract.View view;
    private LibraryAccess api;

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
        if (idBook != 0) {
            view.startProgressBar();
            if (InternetConnection.isConnection(MainActivity.getAppContext())) {
                view.startProgressBar();
                api.reserveBook(LocalDataAccess.getToken(), idBook);
            } else {
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.openOnInternetDialog();
                    }
                },5000);
            }
        }else {
            view.showToast(MainActivity.getAppContext().getString(R.string.choose_book));
        }
    }

    @Override
    public void onReserveBook() {
        view.showSuccessReservationBookToast();
        view.endProgressBar();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.endProgressBar();
        if(error.getStatus()==403){
            view.showStopBorrowDialog();
        }
    }
}