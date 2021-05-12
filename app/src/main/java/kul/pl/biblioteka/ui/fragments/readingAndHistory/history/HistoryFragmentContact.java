package kul.pl.biblioteka.ui.fragments.readingAndHistory.history;

import java.util.List;

import kul.pl.biblioteka.models.HistoryBookModel;
import kul.pl.biblioteka.models.ReservationBookModel;

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
    }
}
