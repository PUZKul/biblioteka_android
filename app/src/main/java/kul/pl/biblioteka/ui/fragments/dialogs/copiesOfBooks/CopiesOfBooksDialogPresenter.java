package kul.pl.biblioteka.ui.fragments.dialogs.copiesOfBooks;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.models.BookModel;
import kul.pl.biblioteka.utils.Direction;
import kul.pl.biblioteka.utils.PageHolder;
import kul.pl.biblioteka.utils.Sorting;

import static kul.pl.biblioteka.utils.Constants.LIMIT;

public class CopiesOfBooksDialogPresenter extends APIAdapter {

    private CopiesOfBooksDialogContract.View view;
    private LibraryAccess api;

    public CopiesOfBooksDialogPresenter(CopiesOfBooksDialogContract.View view) {
        this.view = view;
        api=LibraryAccess.getInstance();
        api.setListener(this);
    }

    public void setList(){
        api.getBooks(LIMIT, 0, Sorting.POPULARITY, Direction.ASC);
    }

    @Override
    public void onBookListReceive(PageHolder<BookModel> page) {
        view.setList(page.getContent());
    }
}