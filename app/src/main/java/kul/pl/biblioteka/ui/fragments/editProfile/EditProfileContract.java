package kul.pl.biblioteka.ui.fragments.editProfile;

import kul.pl.biblioteka.models.RegistrationUserModel;


public interface EditProfileContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openMainActivity();

        void checkBoxStatus();

        void hideEditPasswordsFields();

        void showEditPasswordsFields();

        void errorEmailIncorrect();

        void errorPasswordIncorrect();

        void errorRepeatPasswordAreNotIdentical();

        void openOnInternetActivity();

        void setEmail(String email);

        String getEmail();

        String getPassword();

        String getRepeatPassword();


    }

    interface Presenter {
        void onSaveClicked(RegistrationUserModel user);

        void setUserDetails();

    }
}