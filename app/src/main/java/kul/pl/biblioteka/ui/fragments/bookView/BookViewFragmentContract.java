package kul.pl.biblioteka.ui.fragments.bookView;

import android.net.Uri;

public interface BookViewFragmentContract {
    interface View {

        void setTitle(String title);

        void setAuthor(String author);

        void setPublisher(String publisher);

        void setPages(String pages);

        void setImage(Uri uriImage);

        void setDate(String date);

        void setAvailabilyty(String status);

        void setStars(double number);

        void startProgressBar();

        void endProgressBar();
    }

    interface Presenter {
    }
}
