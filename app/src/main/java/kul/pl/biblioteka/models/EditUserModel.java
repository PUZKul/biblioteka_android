package kul.pl.biblioteka.models;

public class EditUserModel {

    private String email;
    private String newPassword;
    private String oldPassword;

    public EditUserModel(String email, String newPassword, String oldPassword) {
        this.email = email;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }
}
