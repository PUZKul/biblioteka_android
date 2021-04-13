package kul.pl.biblioteka.ui.fragments.bookView;

import android.net.Uri;

import kul.pl.biblioteka.dataAccess.APIListener;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.utils.Helper;
import kul.pl.biblioteka.utils.PageHolder;

public class BookViewFragmentPresenter implements BookViewFragmentContract.Presenter, APIListener {

    private LibraryAccess api;
    private BookViewFragmentContract.View view;

    public BookViewFragmentPresenter(BookViewFragmentContract.View view,int idBook) {
        this.view = view;
        api = LibraryAccess.getInstance();
        view.startProgressBar();
        api.setListener(this);
        setBook(idBook);
    }

    private void setBookDetails(BookModel book) {
        view.setTitle(book.getTitle());
        view.setAuthor(book.getAuthors());
        view.setPublisher(book.getPublisher());
        view.setPages(book.getPages()+"");
        view.setStars(book.getRating());
        view.setDate(Helper.getDefaultDateFormat(book.getYear()));
        view.setImage(Uri.parse(book.getImageUrl()));
        view.endProgressBar();
    }

    private void setBook(int idBook) {
        api.getBookById(idBook);
        api.getAvailableBookNumber(idBook);
    }

    @Override
    public void onBookListReceive(PageHolder<BookModel> page) {

    }


    @Override
    public void onErrorReceive(ApiError error) {

    }

    @Override
    public void onBookReceive(BookModel book) {
        setBookDetails(book);
    }

    @Override
    public void onAvailableBook(Integer available) {
        view.setAvailabilyty(available.toString());
    }
}
