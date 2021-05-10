package kul.pl.biblioteka.ui.fragments.firstWindow;

import java.util.List;

import kul.pl.biblioteka.models.BookModel;

public interface FirstWindowFragmentContract {
    interface Presenter {
        void setListSortByTitle();

        void setListSortByRating();

        void setListSortByDate();

        void setListTopBooks();

        void setListSortByDiscover();

        void setListByName(String bookName);
    }

    interface View {
        void setTheMostPopularList(List<BookModel> booksList);

        void setRecommendedList(List<BookModel> booksList);

        void showToast(String text);

        String getSearchText();

        void startProgressBar();

        void endProgressBar();

        void openOnInternetActivity();

    }
}
