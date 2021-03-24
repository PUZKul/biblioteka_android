package kul.pl.biblioteka.ui.fragments.home;

import java.util.List;

import kul.pl.biblioteka.models.BookModel;

public interface HomeFragmentContract {
    interface Presenter{
        void setListSortByName();
        void setListSortByTitle();
        void setListSortByRating();
        void setListSortByDate();
        void setListTopBooks();
        void setListSortByDiscover();
    }
    interface View{
        void setList(List<BookModel> booksList);
        void showToast(String text);
        String getSearchText();
    }
}
