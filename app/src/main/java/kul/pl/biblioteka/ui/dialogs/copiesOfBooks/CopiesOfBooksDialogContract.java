package kul.pl.biblioteka.ui.dialogs.copiesOfBooks;

import java.util.List;

import kul.pl.biblioteka.models.CopiesOfBookModel;

interface CopiesOfBooksDialogContract {

    interface Presenter{

    }

    interface View{

        void setList(List<CopiesOfBookModel> books);
    }
}
