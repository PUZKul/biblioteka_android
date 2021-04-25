package kul.pl.biblioteka.ui.fragments.bookView;

import android.net.Uri;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.utils.Helper;

public class BookViewFragmentPresenter extends APIAdapter implements BookViewFragmentContract.Presenter {

    private LibraryAccess api;
    private BookViewFragmentContract.View view;
    private int idBook;

    public BookViewFragmentPresenter(BookViewFragmentContract.View view,int idBook) {
        this.view = view;
        api = LibraryAccess.getInstance();
        view.startProgressBar();
        this.idBook=idBook;
        api.setListener(this);
        setBook(idBook);
    }

    public int getIdBook() {
        return idBook;
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
    public void onBookReceive(BookModel book) {
        setBookDetails(book);
    }

    @Override
    public void onAvailableBook(Integer available) {
        view.setAvailability(available.toString());
    }

    @Override
    public void onNoInternet() {
        view.endProgressBar();
        view.openOnInternetActivity();
    }

}
