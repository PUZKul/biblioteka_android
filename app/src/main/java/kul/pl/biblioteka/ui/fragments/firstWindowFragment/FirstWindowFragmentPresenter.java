package kul.pl.biblioteka.ui.fragments.firstWindowFragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.models.BookModel;

import kul.pl.biblioteka.utils.Direction;
import kul.pl.biblioteka.utils.PageHolder;
import kul.pl.biblioteka.utils.PaginationBar;
import kul.pl.biblioteka.utils.Sorting;

import static kul.pl.biblioteka.utils.Constants.LIMIT;

public class FirstWindowFragmentPresenter extends APIAdapter implements FirstWindowFragmentContract.Presenter {

    private FirstWindowFragmentContract.View view;
    private LibraryAccess api;
    private PaginationBar pageBar;
    private Sorting currentSorting;
    private Direction currentDirection;
    private String currentSearch;
    private Context context;

    public FirstWindowFragmentPresenter(FirstWindowFragmentContract.View view,Context context) {
        this.view = view;
        this.api = LibraryAccess.getInstance();
        api.setListener(this);
        this.context=context;
    }

    public void setPaginationComponent(View view) {
        pageBar = new PaginationBar(view);
        pageBar.setOnNextClickListener(nextClickListener);
        pageBar.setOnPreviousClickListener(previousClickListener);
        pageBar.setOnPageClickListener(pageClickListener);
    }

    @Override
    public void setListSortByTitle() {
        if (InternetConnection.isConnection(context)) {
            currentSorting = Sorting.TITLE;
            api.getBooks(LIMIT, 0, currentSorting);
        }else
            view.openOnInternetActivity();
    }

    @Override
    public void setListSortByRating() {
        if (InternetConnection.isConnection(context)) {
            currentSorting = Sorting.RATING;
            currentDirection = Direction.DESC;
            api.getBooks(LIMIT, 0, currentSorting, currentDirection);
        }else
            view.openOnInternetActivity();
    }

    @Override
    public void setListSortByDate() {
        if (InternetConnection.isConnection(context)) {
            currentSorting = Sorting.YEAR;
            currentDirection = Direction.DESC;
            api.getBooks(LIMIT, 0, currentSorting, currentDirection);
        }else
            view.openOnInternetActivity();
    }

    @Override
    public void setListTopBooks() {
        if (InternetConnection.isConnection(context)) {
            currentSorting = Sorting.POPULARITY;
            currentDirection = Direction.DESC;
            api.getBooks(LIMIT, 0, currentSorting, currentDirection);
        }else
            view.openOnInternetActivity();
    }

    @Override
    public void setListSortByDiscover() {
        if (InternetConnection.isConnection(context))
            api.getDiscoverBooks(LIMIT);
        else
            view.openOnInternetActivity();
    }

    @Override
    public void setListByName(String search) {
        if(InternetConnection.isConnection(context)){
            currentSorting = Sorting.SEARCH;
            currentSearch = search;
            api.getSearchBooks(LIMIT, 0, currentSearch);
        }else
            view.openOnInternetActivity();
    }

    @Override
    public void onBookListReceive(PageHolder<BookModel> page) {
        view.startProgressBar();
        view.setTheMostPopularList(page.getContent());
        view.endProgressBar();
        pageBar.setPage(page);
    }

    @Override
    public void onNoInternet() {
        view.openOnInternetActivity();
        view.endProgressBar();
    }

    private View.OnClickListener previousClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(InternetConnection.isConnection(context)){
                if (currentSorting.equals(Sorting.SEARCH))
                    api.getSearchBooks(LIMIT, pageBar.previousPage(), currentSearch);
                else
                    api.getBooks(LIMIT, pageBar.previousPage(), currentSorting, currentDirection);
            }else
                view.openOnInternetActivity();
        }
    };

    private View.OnClickListener nextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           if(InternetConnection.isConnection(context)){
               if (currentSorting.equals(Sorting.SEARCH))
                   api.getSearchBooks(LIMIT, pageBar.nextPage(), currentSearch);
               else
                   api.getBooks(LIMIT, pageBar.nextPage(), currentSorting, currentDirection);
           }else
               view.openOnInternetActivity();
        }
    };

    private final View.OnClickListener pageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(InternetConnection.isConnection(context)){
                TextView text = pageBar.getView().findViewById(v.getId());
                String value = text.getText().toString();
                int clickedPage = Integer.parseInt(value) - 1;
                if (currentSorting.equals(Sorting.SEARCH))
                    api.getSearchBooks(LIMIT, clickedPage, currentSearch);
                else
                    api.getBooks(LIMIT, clickedPage, currentSorting, currentDirection);
            }else
                view.openOnInternetActivity();
        }
    };

    @Override
    public void onDiscoverBookListReceive(PageHolder<BookModel> page) {
        if(InternetConnection.isConnection(context)){
            if(this.currentSorting==null)
                view.setTheMostPopularList(page.getContent());
            view.setRecommendedList(page.getContent());
        }else
            view.openOnInternetActivity();
    }
}
