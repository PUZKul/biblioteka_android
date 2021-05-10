package kul.pl.biblioteka.ui.fragments.readingAndHistory.history;

import java.util.List;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.HistoryBookModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.utils.PageHolder;

public class HistoryFragmentPresenter extends APIAdapter implements HistoryFragmentContact.Presenter{

    private HistoryFragmentContact.View view;
    private LibraryAccess api;

    public HistoryFragmentPresenter(HistoryFragmentContact.View view) {
        this.view = view;
        this.api=LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void setList() {
        view.setDarkList();
        setHistoryBookList();
    }

    private void setHistoryBookList(){
        if(InternetConnection.isConnection(MainActivity.getAppContext())){
            view.startProgressBar();
            api.getHistoryBooks(10,0,LocalDataAccess.getToken());
        }
    }

    @Override
    public void onHistoryBooksReceive(PageHolder<HistoryBookModel> books) {
        if(books.getContent().size()!=0)
            view.setList(books.getContent());
        else
            view.setEmptyLayout();
        view.endProgressBar();
    }

    @Override
    public void onErrorReceive(ApiError error) {

    }
}
