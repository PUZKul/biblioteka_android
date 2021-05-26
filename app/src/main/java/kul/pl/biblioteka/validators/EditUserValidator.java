package kul.pl.biblioteka.validators;

import kul.pl.biblioteka.models.EditUserModel;


public class EditUserValidator {
    public static boolean validateUser(EditUserModel user) {
        return !user.getAddress().isEmpty()
                && !user.getEmail().isEmpty()
                && !user.getFirstName().isEmpty()
                && user.getLastName().isEmpty();
    }
}
