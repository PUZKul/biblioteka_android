package kul.pl.biblioteka.dataAccess;

import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.models.UserModel;
import kul.pl.biblioteka.utils.PageHolder;

public interface APIListener {
    void onBookListReceive(PageHolder<BookModel> page);

    void onErrorReceive(ApiError error);

    void onBookReceive(BookModel book);

    void onAvailableBook(Integer available);

    void onLoginSuccesses();

    void onNoInternet();

    void onRegistrationSuccesses();

    void onUserDetailsReceive(UserModel user);

}
