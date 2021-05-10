package kul.pl.biblioteka.ui.fragments.readingAndHistory.reservation;

import java.util.List;

import kul.pl.biblioteka.models.ReservationBookModel;

public interface ReservationFragmentContact {
    interface View{
        void setList(List<ReservationBookModel> books);

        void setDarkList();

        void setEmptyLayout();

        void startProgressBar();

        void endProgressBar();
    }
    interface Presenter{
        void setList();
    }
}
