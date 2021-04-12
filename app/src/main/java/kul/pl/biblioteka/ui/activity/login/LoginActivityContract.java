package kul.pl.biblioteka.ui.activity.login;

import kul.pl.biblioteka.models.LoginUserModel;

public interface LoginActivityContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openMainActivity();
    }

    interface Presenter {
        void onLoginClicked(LoginUserModel user);
    }
}
