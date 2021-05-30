package kul.pl.biblioteka.ui.activity.register;

import android.content.Context;
import android.os.Handler;

import kul.pl.biblioteka.R;
import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.InternetConnection;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.LoginApiUserModel;
import kul.pl.biblioteka.models.RegistrationApiUserModel;
import kul.pl.biblioteka.models.RegistrationUserModel;
import kul.pl.biblioteka.ui.activity.MainActivity;
import kul.pl.biblioteka.utils.StringHelper;

public class RegisterActivityPresenter extends APIAdapter implements RegisterActivityContract.Presenter {
    private RegisterActivityContract.View view;
    private LibraryAccess api;
    private Context context;

    public RegisterActivityPresenter(RegisterActivityContract.View view, Context context) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
        this.context = context;
    }

    @Override
    public void onRegisterClicked(RegistrationUserModel user) {
        if (!isEmptyFields(user)
                && validateFields(user)) {
            if (InternetConnection.isConnection(context)) {
                view.startProgressBar();
                api.getRegistration(new RegistrationApiUserModel(
                        user.getNick()
                        , user.getEmail()
                        , user.getPasswordFirst()
                ));
            } else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.openOnInternetDialog();
                    }
                }, 5000);
            }
        }
    }

    @Override
    public void loginUser(LoginApiUserModel model) {
        api.getAuthorization(model);
    }

    private boolean validateFields(RegistrationUserModel user) {
        if (!StringHelper.validateLoginRegistration(user.getNick())) {
            view.errorNickIncorrect();
            return false;
        } else if (!StringHelper.validateEmailRegistration(user.getEmail())) {
            view.errorEmailIncorrect();
            return false;
        } else if (!StringHelper.validatePasswordRegistration(user.getPasswordFirst())) {
            view.errorPasswordIncorrect();
            return false;
        } else if (!StringHelper.validateTwoPasswordRegistration(user.getPasswordFirst(), user.getPasswordSecond())) {
            view.errorRepeatPasswordAreNotIdentical();
            return false;
        }
        return true;
    }

    private boolean isEmptyFields(RegistrationUserModel user) {
        if (user.getNick().isEmpty()) {
            view.errorNickIsEmpty();
            return true;
        } else if (user.getEmail().isEmpty()) {
            view.errorEmailIsEmpty();
            return true;
        } else if (user.getPasswordFirst().isEmpty()) {
            view.errorPasswordIsEmpty();
            return true;
        } else if (user.getPasswordSecond().isEmpty()) {
            view.errorRepeatPasswordIsEmpty();
            return true;
        }
        return false;
    }

    @Override
    public void onRegistrationSuccesses() {
        view.onSuccessRegistration();
    }

    @Override
    public void onLoginSuccesses() {
        view.endProgressBar();
        view.showToast(String.valueOf(MainActivity.getAppContext().getString(R.string.successful_registration)));
        view.openContinueDialog();
    }

    @Override
    public void onErrorReceive(ApiError error) {
        view.endProgressBar();
        System.out.println(error.getStatus());
        if (error.getStatus() == 409)
            view.showToast(String.valueOf(MainActivity.getAppContext().getString(R.string.user_with_given_email_or_logiin_exist)));
    }

    @Override
    public void onRefreshServer() {
        view.onRefresh();
    }
}
