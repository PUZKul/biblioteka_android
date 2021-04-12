package kul.pl.biblioteka.ui.activity.register;

import kul.pl.biblioteka.models.RegistrationUserModel;

public interface RegisterActivityContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openMainActivity();

    }

    interface Presenter {
        void onRegisterClicked(RegistrationUserModel user);
    }
}
