package kul.pl.biblioteka.ui.fragments.editProfile;

import kul.pl.biblioteka.models.RegistrationUserModel;


public interface EditProfileContract {
    interface View{
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openMainActivity();

        void errorEmailIncorrect();

        void errorEmailIsEmpty();

        void errorPasswordIsEmpty();

        void errorPasswordIncorrect();

        void errorRepeatPasswordAreNotIdentical();

        void openOnInternetActivity();
    }

    interface Presenter {
        void onSaveClicked(RegistrationUserModel user);
    }
}