package kul.pl.biblioteka.ui.fragments.readingAndHistory.history;


import android.view.View;

import java.util.List;

import kul.pl.biblioteka.models.HistoryBookModel;

public interface HistoryFragmentContact {
    interface View{
        void setList(List<HistoryBookModel> books);

        void setDarkList();

        void setEmptyLayout();

        void startProgressBar();

        void endProgressBar();

        void openOnInternetDialog();


    }
    interface Presenter{
        void setList();

        void setPaginationComponent(android.view.View view);
    }
}
