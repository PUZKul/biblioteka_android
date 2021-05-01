package kul.pl.biblioteka.ui.fragments.profile;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.dataAccess.local.LocalDataAccess;
import kul.pl.biblioteka.models.UserBookDetails;
import kul.pl.biblioteka.models.UserModel;

public class ProfileFragmentPresenter extends APIAdapter implements ProfileFragmentContact.Presenter {

    private ProfileFragmentContact.viev view;
    private LibraryAccess api;

    public ProfileFragmentPresenter(ProfileFragmentContact.viev view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void onUserBooksDetailsReceive(UserBookDetails details) {
        view.setReadBooks(details.getTotalBooks()+"");
        view.setCurrentBooks(details.getCurrentBooks()+"");
    }

    @Override
    public void onUserDetailsReceive(UserModel user) {
        view.setNick(user.getUsername());
        view.setExperience(user.getPoints());
        //todo create class counts level
    }

    @Override
    public void setUSeaDetails() {
        api.getUserDetails(LocalDataAccess.getToken());
        api.getUserBooksDetails(LocalDataAccess.getToken());
    }

    @Override
    public void logoutUser() {
        LocalDataAccess.clean();
        view.openLoginActivity();
    }
}
