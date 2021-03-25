package kul.pl.biblioteka.api;

import java.util.List;

import kul.pl.biblioteka.models.BookModel;

public interface APIListener {
    void onBookListReceive(List<BookModel> books);
    //void onErrorReceive(ApiError error);
    void onBookReceive(BookModel book);
}
