package kul.pl.biblioteka.ui.fragments.home;

import java.util.List;

import kul.pl.biblioteka.dataAccess.APIListener;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.utils.Sorting;

public class HomeFragmentPresenter implements HomeFragmentContract.Presenter, APIListener {

    private HomeFragmentContract.View view;
    private LibraryAccess api;

    public HomeFragmentPresenter(HomeFragmentContract.View view) {
        this.view = view;
        this.api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void setListSortByTitle() {
        api.getBooks(10,1, Sorting.TITLE);
    }

    @Override
    public void setListSortByRating() {
        api.getBooks(10,1, Sorting.RATING);
    }

    @Override
    public void setListSortByDate() {
        api.getBooks(10,1, Sorting.YEAR);
    }

    @Override
    public void setListTopBooks() {
        api.getBooks(10,1, Sorting.POPULARITY);
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
}