package kul.pl.biblioteka.ui.fragments.bookView;

import kul.pl.biblioteka.api.API;
import kul.pl.biblioteka.models.BookModel;

public class BookViewFragmentPresenter implements BookViewFragmentContract.Presenter {

    private API api;
    private BookModel book;
    private BookViewFragmentContract.View view;
    private String idBook;

    public BookViewFragmentPresenter(BookViewFragmentContract.View view,String idBook) {
        this.view = view;
        api = new API();
        view.startProgressBar();
        this.idBook=idBook;
        book = setBook();
    }

    @Override
    public void setBookDetails() {
        view.setAuthor(book.getAuthors());
        //ToDo set book detalist  using all methods with view and gets with book
        view.endProgressBar();
    }

    private BookModel setBook() {
        return api.getBook(idBook);
    }
}
