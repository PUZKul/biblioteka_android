package kul.pl.biblioteka.ui.activity.login;

import kul.pl.biblioteka.models.UserModel;

public interface LoginActivityContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openMainActivity();
    }

    interface Presenter {
        void onLoginClicked(UserModel user);
    }
}
