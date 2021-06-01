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
    public void onRefreshServer() {
    }

    @Override
    public void onRegistrationSuccesses() {

    }

    @Override
    public void onUserDetailsReceive(UserModel user) {

    }

    @Override
    public void onCopiesOfBookReceive(List<CopiesOfBookModel> book) {

    }

    @Override
    public void onDiscoverBookListReceive(PageHolder<BookModel> page) {

    }

    @Override
    public void onUserBooksDetailsReceive(UserBookDetails details) {

    }

    @Override
    public void onEditUserReceive() {

    }

    @Override
    public void onHistoryBooksReceive(PageHolder<HistoryBookModel> books) {

    }

    @Override
    public void onReserveBook() {

    }

    @Override
    public void onCancelReservationReceive() {

    }

    @Override
    public void onCurrentBooksReceive(PageHolder<HistoryBookModel> books) {

    }

    @Override
    public void onReservationBooksReceive(PageHolder<ReservationBookModel> books) {

    }

    @Override
    public void onExtendBookRentalReceive() {

    }

    @Override
    public void onIncreaseLimitReceive() {

    }
}
