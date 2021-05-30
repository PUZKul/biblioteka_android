package kul.pl.biblioteka.ui.activity.register;

import kul.pl.biblioteka.models.LoginApiUserModel;
import kul.pl.biblioteka.models.RegistrationUserModel;

public interface RegisterActivityContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openContinueDialog();

        void errorNickIncorrect();

        void errorNickIsEmpty();

        void errorEmailIncorrect();

        void errorEmailIsEmpty();

        void errorPasswordIsEmpty();

        void errorPasswordIncorrect();

        void errorRepeatPasswordAreNotIdentical();

        void openOnInternetDialog();

        void onSuccessRegistration();

        void onRefresh();

    }

    interface Presenter {
        void onRegisterClicked(RegistrationUserModel user);

        void loginUser(LoginApiUserModel model);

    }
}
