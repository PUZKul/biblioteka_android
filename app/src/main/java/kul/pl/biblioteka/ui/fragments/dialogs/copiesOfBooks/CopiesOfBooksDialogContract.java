package kul.pl.biblioteka.ui.fragments.dialogs.copiesOfBooks;

import java.util.List;

import kul.pl.biblioteka.models.BookModel;

interface CopiesOfBooksDialogContract {

    interface Presenter{

    }

    interface View{

        void setList(List<BookModel> books);
    }
}
