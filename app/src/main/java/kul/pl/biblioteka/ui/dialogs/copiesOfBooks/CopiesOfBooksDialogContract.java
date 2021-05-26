package kul.pl.biblioteka.ui.dialogs.copiesOfBooks;

import java.util.List;

import kul.pl.biblioteka.models.CopiesOfBookModel;

interface CopiesOfBooksDialogContract {

    interface Presenter{

        void reserveBook(long idBook);

    }

    interface View{

        void setList(List<CopiesOfBookModel> books);

        void showSuccessReservationBookToast();

        void startProgressBar();

        void endProgressBar();

        void openOnInternetDialog();

        void openInformDialog();

        void showToast(String message);

        void showStopBorrowDialog();
    }
}
