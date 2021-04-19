package kul.pl.biblioteka.dataAccess;

import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.utils.PageHolder;

public abstract class APIAdapter implements APIListener {

    @Override
    public void onBookListReceive(PageHolder<BookModel> page) {

    }

    @Override
    public void onBookReceive(BookModel book) {

    }

    @Override
    public void onErrorReceive(ApiError error) {

    }

    @Override
    public void onAvailableBook(Integer available) {

    }

    @Override
    public void onLoginSuccesses() {

    }

    @Override
    public void onNoInternet() {

    }

    @Override
    public void onRegistrationSuccesses() {

    }
}
