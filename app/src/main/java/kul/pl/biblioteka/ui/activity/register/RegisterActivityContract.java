package kul.pl.biblioteka.ui.activity.register;

import kul.pl.biblioteka.models.RegistrationUserModel;

public interface RegisterActivityContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openMainActivity();

        void errorNickIncorrect();
        void errorNickExist();
        void errorNickIsEmpty();


        void errorEmailExist();
        void errorEmailIncorrect();
        void errorEmailIsEmpty();

        void errorPasswordIsEmpty();
        void errorPasswordIncorrect();

        void errorRepeatPasswordIsEmpty();
        void errorRepeatPasswordAreNotIdentical();


    }

    interface Presenter {
        void onRegisterClicked(RegistrationUserModel user);
    }
}
