package kul.pl.biblioteka.ui.fragments.bookView;

import java.util.List;

import kul.pl.biblioteka.dataAccess.APIListener;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.BookModel;

public class BookViewFragmentPresenter implements BookViewFragmentContract.Presenter, APIListener {

    private LibraryAccess api;
    private BookModel book;
    private BookViewFragmentContract.View view;
    private int idBook;

    public BookViewFragmentPresenter(BookViewFragmentContract.View view,int idBook) {
        this.view = view;
        api = LibraryAccess.getInstance();
        view.startProgressBar();
        this.idBook=idBook;
        setBook();
    }

    @Override
    public void setBookDetails() {
        view.setAuthor(book.getAuthors());
        //ToDo set book detalist  using all methods with view and gets with book
        view.endProgressBar();
    }

    private void setBook() {
        api.getBookById(idBook);
    }

    @Override
    public void onBookListReceive(List<BookModel> books) {

    }

    @Override
    public void onErrorReceive(ApiError error) {

    }

    @Override
    public void onBookReceive(BookModel book) {
        this.book=book;
    }
}
