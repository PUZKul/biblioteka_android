package kul.pl.biblioteka.ui.fragments.readingAndHistory.reservation;

import java.util.List;

import kul.pl.biblioteka.models.HistoryBookModel;
import kul.pl.biblioteka.models.ReservationBookModel;

public interface ReservationFragmentContact {
    interface View{
        void setList(List<ReservationBookModel> books);

        void setDarkList();

        void setEmptyLayout();

        void startProgressBar();

        void endProgressBar();

        void openOnInternetDialog();

        void openCancelReservationDialog();

        void onSuccessCancelBookMessage();

        void showUserBannedDialog();
    }
    interface Presenter{
        void setList();

        void onCancelClicked(int idBook);

        void cancelBook();
    }
}
