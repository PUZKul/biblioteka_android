package kul.pl.biblioteka.dataAccess;

import java.util.List;

import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.exception.ApiErrorParser;
import kul.pl.biblioteka.models.BookModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract class LibraryAPI {
    protected APIListener listener;

    public void setListener(APIListener listener) {
        this.listener = listener;
    }


    protected Callback<List<BookModel>> callbackForBooksList = new Callback<List<BookModel>>() {
        @Override
        public void onResponse(Call<List<BookModel>> call, Response<List<BookModel>> response) {
            if (response.isSuccessful()) {
                List<BookModel> books = response.body();
                listener.onBookListReceive(books);
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<List<BookModel>> call, Throwable t) {
            System.out.println("nie dziala");
        }
    };
    protected Callback<BookModel> callbackForBook = new Callback<BookModel>() {
        @Override
        public void onResponse(Call<BookModel> call, Response<BookModel> response) {
            if (response.isSuccessful()) {
                BookModel book = response.body();
                listener.onBookReceive(book);
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<BookModel> call, Throwable t) {

        }
    };
}