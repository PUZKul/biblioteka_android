package kul.pl.biblioteka.ui.fragments.editProfile;

import kul.pl.biblioteka.models.EditUserModel;
import kul.pl.biblioteka.models.RegistrationUserModel;

public interface EditProfileContract {
    interface View {
        void showToast(String message);

        void startProgressBar();

        void endProgressBar();

        void openMainActivity();

        void errorEmailIncorrect();

        void errorPasswordIncorrect();

        void errorRepeatPasswordAreNotIdentical();

        void openNoInternetDialog();

        void setEmail(String email);

        String getEmail();

        String getPassword();

        String getRepeatPassword();

        boolean isCheckedBoxEditPassword();

        void openDialog();

        void setPhone(String phone);

        void setAddress(String address);

        void setFistName(String fistName);

        void setLastName(String lastName);

        String getLastName();

        String getFirstName();

        String getAddress();

        String getPhone();

        void onRefresh();

        void closeFragment();

        void emailIsEmpty();

        void firstNameIsEmpty();

        void lastNameIsEmpty();

        void addressIsEmpty();

        void phoneIsEmpty();

        void emailErrorMessage();

        void firstNameErrorMessage();

        void lastNameErrorMessage();

        void addressErrorMessage();

        void phoneErrorMessage();
    }

    interface Presenter {
        void onSaveClicked(RegistrationUserModel user);

        void setUserDetails();

        void changeUserData(EditUserModel model);

    }
}