package kul.pl.biblioteka.ui.activity.main;

public interface MainActivityContract {
    interface View {
        void showUserBanedDialog();
    }

    interface Presenter {
        void isBanedUser();
    }
}
