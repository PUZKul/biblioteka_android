package kul.pl.biblioteka.ui.fragments.home;

import android.view.View;
import android.widget.TextView;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.utils.Direction;
import kul.pl.biblioteka.utils.PageHolder;
import kul.pl.biblioteka.utils.PaginationBar;
import kul.pl.biblioteka.utils.Sorting;

import static kul.pl.biblioteka.utils.Constants.LIMIT;

public class HomeFragmentPresenter extends APIAdapter implements HomeFragmentContract.Presenter {

    private HomeFragmentContract.View view;
    private LibraryAccess api;
    private PaginationBar pageBar;
    private Sorting currentSorting;
    private Direction currentDirection;
    private String currentSearch;

    public HomeFragmentPresenter(HomeFragmentContract.View view) {
        this.view = view;
        this.api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    public void setPaginationComponent(View view) {
        pageBar = new PaginationBar(view);
        pageBar.setOnNextClickListener(nextClickListener);
        pageBar.setOnPreviousClickListener(previousClickListener);
        pageBar.setOnPageClickListener(pageClickListener);
    }

    @Override
    public void setListSortByTitle() {
        currentSorting = Sorting.TITLE;
        api.getBooks(LIMIT, 0, currentSorting);
    }

    @Override
    public void setListSortByRating() {
        currentSorting = Sorting.RATING;
        currentDirection = Direction.DESC;
        api.getBooks(LIMIT, 0, currentSorting, currentDirection);
    }

    @Override
    public void setListSortByDate() {
        currentSorting = Sorting.YEAR;
        currentDirection = Direction.DESC;
        api.getBooks(LIMIT, 0, currentSorting, currentDirection);
    }

    @Override
    public void setListTopBooks() {
        currentSorting = Sorting.POPULARITY;
        currentDirection = Direction.DESC;
        api.getBooks(LIMIT, 0, currentSorting, currentDirection);
    }

    @Override
    public void setListSortByDiscover() {
        api.getDiscoverBooks(LIMIT);
    }

    @Override
    public void setListByName(String search) {
        currentSorting = Sorting.SEARCH;
        currentSearch = search;
        api.getSearchBooks(LIMIT, 0, currentSearch);
    }

    @Override
    public void onBookListReceive(PageHolder<BookModel> page) {
        view.startProgressBar();
        view.setList(page.getContent());
        view.endProgressBar();
        pageBar.setPage(page);
    }

    @Override
    public void onAvailableBook(Integer available) {

    }

    @Override
    public void onNoInternet() {
        view.openOnInternetActivity();
        view.endProgressBar();
    }

    private View.OnClickListener previousClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (currentSorting.equals(Sorting.SEARCH))
                api.getSearchBooks(LIMIT, pageBar.previousPage(), currentSearch);
            else
                api.getBooks(LIMIT, pageBar.previousPage(), currentSorting, currentDirection);
        }
    };

    private View.OnClickListener nextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (currentSorting.equals(Sorting.SEARCH))
                api.getSearchBooks(LIMIT, pageBar.nextPage(), currentSearch);
            else
                api.getBooks(LIMIT, pageBar.nextPage(), currentSorting, currentDirection);
        }
    };

    private final View.OnClickListener pageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView text = pageBar.getView().findViewById(v.getId());
            String value = text.getText().toString();
            int clickedPage = Integer.parseInt(value) - 1;
            if (currentSorting.equals(Sorting.SEARCH))
                api.getSearchBooks(LIMIT, clickedPage, currentSearch);
            else
                api.getBooks(LIMIT, clickedPage, currentSorting, currentDirection);
        }
    };
}