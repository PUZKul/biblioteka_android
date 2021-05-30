package kul.pl.biblioteka.ui.activity.login;

import android.os.Handler;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.LoginApiUserModel;
import kul.pl.biblioteka.models.LoginUserModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
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
            if (InternetConnection.isConnection(MainActivity.getAppContext()))
                api.getAuthorization(new LoginApiUserModel(user.getNick(), user.getPassword()));
            else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.openOnInternetDialog();
                    }
                }, 5000);
            }
        } else
            view.endProgressBar();
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
            view.showToast(MainActivity.getAppContext().getString(R.string.incorrect_data));
            return false;
        }
        return true;
    }

    @Override
    public void onLoginSuccesses() {
        view.endProgressBar();
        view.openMainActivity();
        view.showToast(MainActivity.getAppContext().getString(R.string.login_successes));
    }

    @Override
    public void onRefreshServer() {
        view.onRefresh();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.showToast(MainActivity.getAppContext().getString(R.string.incorrect_data));
        view.endProgressBar();
    }
}