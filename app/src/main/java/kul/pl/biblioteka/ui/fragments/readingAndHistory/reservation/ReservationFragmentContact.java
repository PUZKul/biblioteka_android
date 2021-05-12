package kul.pl.biblioteka.ui.fragments.readingAndHistory.reservation;

import java.util.List;

import kul.pl.biblioteka.models.HistoryBookModel;

public interface ReservationFragmentContact {
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
    }
}
