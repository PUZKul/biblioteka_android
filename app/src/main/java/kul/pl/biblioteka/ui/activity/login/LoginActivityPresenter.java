package kul.pl.biblioteka.ui.activity.login;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.LoginApiUserModel;
import kul.pl.biblioteka.models.LoginUserModel;
import kul.pl.biblioteka.utils.StringHelper;

public class LoginActivityPresenter extends APIAdapter implements LoginActivityContract.Presenter {

    private LoginActivityContract.View view;
    private LibraryAccess api;

    public LoginActivityPresenter(LoginActivityContract.View view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        this.view = view;
        api.setListener(this);
    }

    @Override
    public void onLoginClicked(LoginUserModel user) {
        view.startProgressBar();
        if (validateDate(user)) {
            view.startProgressBar();
            api.getAuthorization(new LoginApiUserModel(user.getNick(), user.getPassword()));
        }
    }

    private boolean validateDate(LoginUserModel user) {
        if (user.getNick().isEmpty() || user.getEmail().isEmpty()) {
            view.errorEmptyLogin();
            return false;
        } else if (user.getPassword().isEmpty()) {
            view.errorEmptyPassword();
            return false;
        } else if (!StringHelper.validateNickLogin(user.getNick())
                || !StringHelper.validatePasswordLogin(user.getPassword())) {
            view.showToast("Incorrect data");
            return false;
        }
        return true;
    }

    @Override
    public void onLoginSuccesses() {
        view.endProgressBar();
        view.openMainActivity();
        view.showToast("Login successes");
    }

    @Override
    public void onNoInternet() {
        view.endProgressBar();
        //w view.openOnInternetActivity();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.showToast("Incorrect data");
        view.endProgressBar();
    }
}
