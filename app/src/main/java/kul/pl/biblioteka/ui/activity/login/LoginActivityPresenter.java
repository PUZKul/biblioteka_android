package kul.pl.biblioteka.ui.activity.login;

import kul.pl.biblioteka.R;
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
        if (!validateDate(user)) {
            view.showToast(String.valueOf(R.string.incorect_data));
            view.endProgressBar();
        } else {
            api.getAuthorization(new LoginApiUserModel(user.getNick(), user.getPassword()));
        }
    }

    private boolean validateDate(LoginUserModel user) {
        return !user.getEmail().isEmpty()
                && !user.getNick().isEmpty()
                && StringHelper.validatePasswordLogin(user.getPassword())
                && StringHelper.validateNickLogin(user.getNick());
    }

    @Override
    public void onLoginSuccesses() {
        view.endProgressBar();
        view.openMainActivity();
    }

    @Override
    public void onNoInternet() {
        view.openOnInternetActivity();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.showToast(String.valueOf(R.string.incorect_data));
    }
}
