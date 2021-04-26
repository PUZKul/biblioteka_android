package kul.pl.biblioteka.ui.dialogs.copiesOfBooks;

import java.util.List;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.models.CopiesOfBookModel;


public class CopiesOfBooksDialogPresenter extends APIAdapter {

    private CopiesOfBooksDialogContract.View view;
    private LibraryAccess api;

    public CopiesOfBooksDialogPresenter(CopiesOfBooksDialogContract.View view,int idBook) {
        this.view = view;
        api=LibraryAccess.getInstance();
        api.setListener(this);
        api.getCopiesOfBook(idBook);
    }

    @Override
    public void onCopiesOfBookReceive(List<CopiesOfBookModel> book) {
        view.setList(book);
    }
}