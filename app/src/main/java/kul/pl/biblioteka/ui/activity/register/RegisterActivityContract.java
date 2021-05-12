package kul.pl.biblioteka.ui.activity.register;

import kul.pl.biblioteka.models.RegistrationUserModel;

public interface RegisterActivityContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openMainActivity();

        void errorNickIncorrect();

        void errorNickIsEmpty();

        void errorEmailIncorrect();

        void errorEmailIsEmpty();

        void errorPasswordIsEmpty();

        void errorPasswordIncorrect();

        void errorRepeatPasswordAreNotIdentical();

        void openOnInternetDialog();
    }

    interface Presenter {
        void onRegisterClicked(RegistrationUserModel user);
    }
}
