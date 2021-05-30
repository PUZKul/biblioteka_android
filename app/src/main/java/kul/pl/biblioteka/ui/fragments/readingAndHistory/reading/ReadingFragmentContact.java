package kul.pl.biblioteka.ui.fragments.readingAndHistory.reading;

import java.util.List;

import kul.pl.biblioteka.models.HistoryBookModel;

public interface ReadingFragmentContact {
    interface View {
        void setList(List<HistoryBookModel> books);

        void setDarkList();

        void setEmptyLayout();

        void startProgressBar();

        void endProgressBar();

        void openOnInternetDialog();

        void showSuccessExtendBookRentalMessage();

        void showFailureExtendBookRentalMessage();

        void onRefresh();
    }

    interface Presenter {
        void setList();

        void onExtendBookRentalClicked(int idBook);
    }
}
