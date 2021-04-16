package kul.pl.biblioteka.dataAccess;

import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.exception.ApiErrorParser;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.utils.PageHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract class LibraryAPI {
    protected APIListener listener;

    public void setListener(APIListener listener) {
        this.listener = listener;
    }


    protected Callback<PageHolder<BookModel>> callbackForBooksList = new Callback<PageHolder<BookModel>>() {
        @Override
        public void onResponse(Call<PageHolder<BookModel>> call, Response<PageHolder<BookModel>> response) {
            if (response.isSuccessful()) {
                PageHolder<BookModel> page = response.body();
                listener.onBookListReceive(page);
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<PageHolder<BookModel>> call, Throwable t) {
            listener.onNoInternet();
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
            listener.onNoInternet();
        }
    };

    protected Callback<Integer> callbackForAvailableBook = new Callback<Integer>() {
        @Override
        public void onResponse(Call<Integer> call, Response<Integer> response) {
            if (response.isSuccessful()) {
                Integer available = response.body();
                listener.onAvailableBook(available);
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<Integer> call, Throwable t) {
            listener.onNoInternet();
        }
    };

    protected Callback<Void> callbackForLoginAuthorization = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if(response.isSuccessful()){
                    listener.onLoginSuccesses();
            } else {
                ApiError apiError = ApiErrorParser.parseError(response);
                listener.onErrorReceive(apiError);
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            listener.onNoInternet();
        }
    };
}