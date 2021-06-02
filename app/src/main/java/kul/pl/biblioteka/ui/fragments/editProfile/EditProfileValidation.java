package kul.pl.biblioteka.ui.fragments.editProfile;

import kul.pl.biblioteka.utils.StringHelper;

class EditProfileValidation {
    public static boolean validateEditProfileFields(EditProfileContract.View view) {
        if (view.getEmail().isEmpty()) {
            view.emailIsEmpty();
            return false;
        } else if (view.getFirstName().isEmpty()) {
            view.firstNameIsEmpty();
            return false;
        } else if (view.getLastName().isEmpty()) {
            view.lastNameIsEmpty();
            return false;
        } else if (view.getAddress().isEmpty()) {
            view.addressIsEmpty();
            return false;
        } else if (view.getPhone().isEmpty()) {
            view.phoneIsEmpty();
            return false;
        } else if (!StringHelper.validateEmailRegistration(view.getEmail())) {
            view.emailErrorMessage();
            return false;
        } else if (!StringHelper.name(view.getFirstName())) {
            view.firstNameIsEmpty();
            return false;
        } else if (!StringHelper.name(view.getLastName())) {
            view.lastNameErrorMessage();
            return false;
        } else if (!StringHelper.phone(view.getPhone())) {
            view.lastNameErrorMessage();
            return false;
        } else if (!StringHelper.address(view.getAddress())) {
            view.addressErrorMessage();
            return false;
        }
        return true;
    }
}
