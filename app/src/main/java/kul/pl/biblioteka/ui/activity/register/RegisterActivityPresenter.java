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
            view.errorNickIsEmpty();
            view.endProgressBar();
        } else if (user.getEmail().isEmpty()) {
            view.errorEmailIsEmpty();
            view.endProgressBar();
        }else if (user.getPasswordFirst().isEmpty()) {
           view.errorPasswordIsEmpty();
            view.endProgressBar();
        }else if (user.getPasswordSecond().isEmpty()) {
            view.errorRepeatPasswordIsEmpty();
            view.endProgressBar();
        } else if (!StringHelper.validateLoginRegistration(user.getNick())) {
            view.errorNickIncorrect();
            view.endProgressBar();
        }else if (!StringHelper.validateEmailRegistration(user.getEmail())) {
            view.errorEmailIncorrect();
            view.endProgressBar();
        }else if (!StringHelper.validatePasswordRegistration(user.getPasswordFirst())) {
            view.errorPasswordIncorrect();
            view.endProgressBar();
        }else if (!StringHelper.validateTwoPasswordRegistration(user.getPasswordFirst(),user.getPasswordSecond())) {
            view.errorRepeatPasswordAreNotIdentical();
            view.endProgressBar();
        }else {
            view.endProgressBar();
            view.showToast("Successful registration");
            //todo connect with api
            view.openMainActivity();
        }
    }
}
