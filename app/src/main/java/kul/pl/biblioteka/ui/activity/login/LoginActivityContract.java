package kul.pl.biblioteka.ui.activity.login;

import kul.pl.biblioteka.models.UserModel;

public interface LoginActivityContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();
    }

    interface Presenter {
        void onLoginClicked(UserModel user);
    }
}
