package kul.pl.biblioteka.dataAccess;

import java.util.List;

import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.models.CopiesOfBookModel;
import kul.pl.biblioteka.models.HistoryBookModel;
import kul.pl.biblioteka.models.ReservationBookModel;
import kul.pl.biblioteka.models.UserBookDetails;
import kul.pl.biblioteka.models.UserModel;
import kul.pl.biblioteka.utils.PageHolder;

public interface APIListener {
    void onBookListReceive(PageHolder<BookModel> page);

    void onErrorReceive(ApiError error);

    void onBookReceive(BookModel book);

    void onAvailableBook(Integer available);

    void onLoginSuccesses();

    void onRefreshServer();

    void onRegistrationSuccesses();

    void onUserDetailsReceive(UserModel user);

    void onCopiesOfBookReceive(List<CopiesOfBookModel> book);

    void onDiscoverBookListReceive(PageHolder<BookModel> page);

    void onUserBooksDetailsReceive(UserBookDetails details);

    void onEditUserReceive();

    void onHistoryBooksReceive(PageHolder<HistoryBookModel> books);

    void onReserveBook();

    void onCancelReservationReceive();

    void onCurrentBooksReceive(PageHolder<HistoryBookModel> books);

    void onReservationBooksReceive(PageHolder<ReservationBookModel> books);

    void onExtendBookRentalReceive();

    void onIncreaseLimitReceive();
}
