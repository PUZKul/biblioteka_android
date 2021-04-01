package kul.pl.biblioteka.dataAccess;

import java.util.List;

import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.utils.Direction;
import kul.pl.biblioteka.utils.PageHolder;
import kul.pl.biblioteka.utils.Sorting;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HTTPMethods {
    @GET("api/library/books")
    Call<PageHolder<BookModel>> getBooks(@Query("limit") int limit,
                                         @Query("page") int page,
                                         @Query("sort") Sorting sort);

    @GET("api/library/books")
    Call<PageHolder<BookModel>> getBooks(@Query("limit") int limit,
                                         @Query("page") int page,
                                         @Query("sort") Sorting sort,
                                         @Query("order") Direction direction);

    @GET("api/library/books/id/{id}")
    Call<BookModel> getBookById(@Path("id") int bookId);

    @GET("/api/library/books/search")
    Call<PageHolder<BookModel>> getBooks(@Query("limit") int limit,
                                         @Query("page") int page,
                                         @Query("title") String title);

    @GET("/api/library/books/discover/{limit}")
    Call<PageHolder<BookModel>> getBooks(@Query("limit") int limit);

}
