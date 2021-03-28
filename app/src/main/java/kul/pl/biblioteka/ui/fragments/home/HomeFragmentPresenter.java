package kul.pl.biblioteka.ui.fragments.home;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.List;

import kul.pl.biblioteka.dataAccess.APIListener;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.utils.PaginationBar;
import kul.pl.biblioteka.utils.Sorting;

import static kul.pl.biblioteka.utils.Constants.LIMIT;

public class HomeFragmentPresenter implements HomeFragmentContract.Presenter, APIListener {

    private HomeFragmentContract.View view;
    private LibraryAccess api;
    private PaginationBar pageBar;
    private Sorting currentSorting;

    public HomeFragmentPresenter(HomeFragmentContract.View view) {
        this.view = view;
        this.api = LibraryAccess.getInstance();
        api.setListener(this);
        setPaginationComponent();
    }

    private void setPaginationComponent() {
        pageBar = new PaginationBar(((Fragment) view).getActivity());
        pageBar.setOnNextClickListener(nextClickListener);
        pageBar.setOnPreviousClickListener(previousClickListener);
        pageBar.setOnPageClickListener(pageClickListener);
    }

    @Override
    public void setListSortByTitle() {
        currentSorting = Sorting.TITLE;
        api.getBooks(LIMIT,0, currentSorting);
    }

    @Override
    public void setListSortByRating() {
        currentSorting = Sorting.RATING;
        api.getBooks(LIMIT,0, currentSorting);
    }

    @Override
    public void setListSortByDate() {
        currentSorting = Sorting.YEAR;
        api.getBooks(LIMIT,0, currentSorting);
    }

    @Override
    public void setListTopBooks() {
        currentSorting = Sorting.POPULARITY;
        api.getBooks(LIMIT,0, currentSorting);
    }

    @Override
    public void setListSortByDiscover() {
        //view.setList();
    }

    @Override
    public void setListByName(String bookName) {
        //view.setList();
    }

    @Override
    public void onBookListReceive(List<BookModel> books) {
        view.startProgressBar();
        view.setList(books);
        view.endProgressBar();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        //todo
    }

    @Override
    public void onBookReceive(BookModel book) {

    }

    // listeners for pagination bar
    private View.OnClickListener previousClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            api.getBooks(LIMIT, pageBar.previousPage(), currentSorting);
        }
    };

    private View.OnClickListener nextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            api.getBooks(LIMIT, pageBar.nextPage(), currentSorting);
        }
    };


    private final View.OnClickListener pageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView text = ((Activity) view).findViewById(v.getId());
            String value = text.getText().toString();
            int clickedPage = Integer.parseInt(value) - 1;
            api.getBooks(LIMIT, clickedPage, currentSorting);
        }
    };

}