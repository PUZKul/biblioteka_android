package kul.pl.biblioteka.ui.activity.register;

import kul.pl.biblioteka.dataAccess.APIAdapter;
import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.exception.ApiError;
import kul.pl.biblioteka.models.RegistrationApiUserModel;
import kul.pl.biblioteka.models.RegistrationUserModel;
import kul.pl.biblioteka.utils.StringHelper;

public class RegisterActivityPresenter extends APIAdapter implements RegisterActivityContract.Presenter {
    private RegisterActivityContract.View view;
    private LibraryAccess api;

    public RegisterActivityPresenter(RegisterActivityContract.View view) {
        this.view = view;
        api = LibraryAccess.getInstance();
        api.setListener(this);
    }

    @Override
    public void onRegisterClicked(RegistrationUserModel user) {
        if (!isEmptyFields(user)
                && validateFields(user)) {
            view.startProgressBar();
            api.getRegistration(new RegistrationApiUserModel(
                    user.getNick()
                    , user.getEmail()
                    , user.getPasswordFirst()
            ));
        }
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
            view.errorPasswordIsEmpty();
            return true;
        }
        return false;
    }

    @Override
    public void onRegistrationSuccesses() {
        view.endProgressBar();
        view.showToast("Successful registration");
        view.openMainActivity();
    }

    @Override
    public void onErrorReceive(ApiError error) {
      view.endProgressBar();
      if(error.getStatus()==409)
        view.showToast("User with given email or login already exist");
    }

    @Override
    public void onNoInternet() {
        view.endProgressBar();
        view.openOnInternetActivity();
    }
}
