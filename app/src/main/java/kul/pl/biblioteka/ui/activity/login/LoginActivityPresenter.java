package kul.pl.biblioteka.ui.activity.login;

import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.models.LoginUserModel;
import kul.pl.biblioteka.utils.StringHelper;

public class LoginActivityPresenter implements LoginActivityContract.Presenter {

    private LoginActivityContract.View view;
    private LibraryAccess api;

    public LoginActivityPresenter(LoginActivityContract.View view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        this.view = view;
        //todo uncomment when will be implementation in api
        //api.setListener(this);
    }

    @Override
    public void onLoginClicked(LoginUserModel user) {
        view.startProgressBar();
        if (!validateDate(user)) {
            view.showToast("Incorrect data");
            view.endProgressBar();
        } else {
            //todo connect with api
            view.endProgressBar();
            view.openMainActivity();
        }
    }

    private boolean validateDate(LoginUserModel user) {
        return !user.getEmail().isEmpty()
                && !user.getNick().isEmpty()
                && StringHelper.validatePasswordLogin(user.getPassword())
                && StringHelper.validateNickLogin(user.getNick());
    }
}
