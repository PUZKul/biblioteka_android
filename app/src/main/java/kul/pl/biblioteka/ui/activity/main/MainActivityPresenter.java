package kul.pl.biblioteka.ui.activity.main;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;

public class MainActivityPresenter extends APIAdapter implements MainActivityContract.Presenter {

    private LibraryAccess api;
    private MainActivityContract.View view;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void isBanedUser() {
        api.isUserBanned(LocalDataAccess.getToken());
    }
}
