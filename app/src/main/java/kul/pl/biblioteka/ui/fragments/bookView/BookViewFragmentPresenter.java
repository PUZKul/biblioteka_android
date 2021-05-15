package kul.pl.biblioteka.ui.fragments.bookView;

import android.net.Uri;
import android.os.Handler;

import java.text.DecimalFormat;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.utils.Helper;

public class BookViewFragmentPresenter extends APIAdapter implements BookViewFragmentContract.Presenter {

    private LibraryAccess api;
    private BookViewFragmentContract.View view;
    private int idBook;

    public BookViewFragmentPresenter(BookViewFragmentContract.View view,int idBook) {
        this.view = view;
        api = LibraryAccess.getInstance();
        view.startProgressBar();
        this.idBook=idBook;
        api.setListener(this);
    }

    public int getIdBook() {
        return idBook;
    }

    private void setBookDetails(BookModel book) {
        view.setTitle(book.getTitle());
        view.setAuthor(book.getAuthors());
        view.setPublisher(book.getPublisher());
        view.setPages(book.getPages()+"");
        view.setStars(book.getRating());
        view.setDate(Helper.getDefaultDateFormat(book.getYear()));
        view.setImage(Uri.parse(book.getImageUrl()));
        view.setNumberOfStars(getStringRating(book.getRating()));
        view.endProgressBar();
    }

    private String getStringRating(double rating) {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(rating)+ "/5";
    }

    @Override
    public void setBook() {
        if(InternetConnection.isConnection(MainActivity.getAppContext())){
            api.getBookById(idBook);
            api.getAvailableBookNumber(idBook);
        }else
            view.openNoInternetDialog();
    }

    @Override
    public void onBookReceive(BookModel book) {
        setBookDetails(book);
    }

    @Override
    public void onAvailableBook(Integer available) {
        view.setAvailability(available.toString());
    }

    @Override
    public void onNoInternet() {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.endProgressBar();
                view.openNoInternetDialog();
            }
        },5000);
    }
}
