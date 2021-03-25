package kul.pl.biblioteka.dataAccess;


import kul.pl.biblioteka.utils.Direction;
import kul.pl.biblioteka.utils.Sorting;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class LibraryAccess extends LibraryAPI{
    private HTTPMethods HTTPMethods;
    private static LibraryAccess instance;
    private final Retrofit retrofit;

    private LibraryAccess() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://puz-biblioteka.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HTTPMethods = retrofit.create(HTTPMethods.class);
        instance = this;
    }

    public static LibraryAccess getInstance(){
        if(instance == null) return new LibraryAccess();
        else return instance;
    }

    public void getBooks(int limit, int page, Sorting sort){
        HTTPMethods.getBooks(limit, page, sort)
                .enqueue(callbackForBooksList);
    }

    public void getBooks(int limit, int page, Sorting sort, Direction direction){
        HTTPMethods.getBooks(limit, page, sort, direction)
                .enqueue(callbackForBooksList);
    }

    public void getBookById(int bookId){
        HTTPMethods.getBookById(bookId)
                .enqueue(callbackForBook);

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
