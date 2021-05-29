package kul.pl.biblioteka.ui.activity.login;

import kul.pl.biblioteka.models.LoginUserModel;

public interface LoginActivityContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openMainActivity();

        void openOnInternetDialog();

        void errorEmptyLogin();

        void errorEmptyPassword();

        void onRefresh();
    }

    interface Presenter {
        void onLoginClicked(LoginUserModel user);
    }
}
