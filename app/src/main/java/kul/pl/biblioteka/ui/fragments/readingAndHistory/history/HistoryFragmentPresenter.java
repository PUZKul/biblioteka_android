package kul.pl.biblioteka.ui.fragments.readingAndHistory.history;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.HistoryBookModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.utils.PageHolder;
import kul.pl.biblioteka.utils.PaginationBar;

import static kul.pl.biblioteka.utils.Constants.LIMIT;

public class HistoryFragmentPresenter extends APIAdapter implements HistoryFragmentContact.Presenter {

    private HistoryFragmentContact.View view;
    private LibraryAccess api;
    private PaginationBar pageBar;

    public HistoryFragmentPresenter(HistoryFragmentContact.View view) {
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
    public void setPaginationComponent(View view) {
        pageBar = new PaginationBar(view);
        pageBar.setOnNextClickListener(nextClickListener);
        pageBar.setOnPreviousClickListener(previousClickListener);
        pageBar.setOnPageClickListener(pageClickListener);
    }

    private View.OnClickListener previousClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(InternetConnection.isConnection(MainActivity.getAppContext())){
                view.startProgressBar();
                api.getHistoryBooks(LIMIT,pageBar.previousPage(),LocalDataAccess.getToken());

            }else
                openNoInternetDialog();
        }
    };

    private View.OnClickListener nextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(InternetConnection.isConnection(MainActivity.getAppContext())){
                    api.getHistoryBooks(LIMIT, pageBar.nextPage(), LocalDataAccess.getToken());
            }else
                openNoInternetDialog();
        }
    };

    private final View.OnClickListener pageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(InternetConnection.isConnection(MainActivity.getAppContext())){
                TextView text = pageBar.getView().findViewById(v.getId());
                String value = text.getText().toString();
                int clickedPage = Integer.parseInt(value) - 1;
                api.getHistoryBooks(LIMIT,clickedPage,LocalDataAccess.getToken());
            }else
                openNoInternetDialog();
        }
    };

    private void setHistoryBookList() {
        view.startProgressBar();
        if (InternetConnection.isConnection(MainActivity.getAppContext()))
            api.getHistoryBooks(10, 0, LocalDataAccess.getToken());
        else openNoInternetDialog();
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
    public void onHistoryBooksReceive(PageHolder<HistoryBookModel> books) {
        if (books.getContent().size() != 0)
            view.setList(books.getContent());
        else
            view.setEmptyLayout();
        view.endProgressBar();
    }

    @Override
    public void onErrorReceive(ApiError error) {
    }
}
