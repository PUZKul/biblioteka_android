package kul.pl.biblioteka.ui.activity.register;

import kul.pl.biblioteka.dataAccess.LibraryAccess;
import kul.pl.biblioteka.models.RegistrationUserModel;
import kul.pl.biblioteka.utils.StringHelper;

public class RegisterActivityPresenter implements RegisterActivityContract.Presenter {
    private RegisterActivityContract.View view;
    private LibraryAccess api;

    public RegisterActivityPresenter(RegisterActivityContract.View view) {
        this.view = view;
        api = LibraryAccess.getInstance();
    }

    @Override
    public void onRegisterClicked(RegistrationUserModel user) {
        view.startProgressBar();
        if (user.getNick().isEmpty()) {
            view.showToast("Nick is empty");
            view.endProgressBar();
        } else if (user.getEmail().isEmpty()) {
            view.showToast("Email is empty");
            view.endProgressBar();
        }else if (user.getPasswordFirst().isEmpty()) {
            view.showToast("Password is empty");
            view.endProgressBar();
        }else if (user.getPasswordSecond().isEmpty()) {
            view.showToast("Repeat password is empty");
            view.endProgressBar();
        }else if (!StringHelper.validateLoginRegistration(user.getNick())) {
            view.showToast("Incorrect nick");
            view.endProgressBar();
        }else if (!StringHelper.validateEmailRegistration(user.getEmail())) {
            view.showToast("Incorrect email");
            view.endProgressBar();
        }else if (!StringHelper.validatePasswordRegistration(user.getPasswordFirst())) {
            view.showToast("Incorrect password");
            view.endProgressBar();
        }else if (!StringHelper.validateTwoPasswordRegistration(user.getPasswordFirst(),user.getPasswordSecond())) {
            view.showToast("Passwords aren't identical");
            view.endProgressBar();
        }else {
            view.endProgressBar();
            view.showToast("Successful registration");
            //todo connect with api
            view.openMainActivity();
        }
    }
}
