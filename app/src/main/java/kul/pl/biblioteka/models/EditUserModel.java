package kul.pl.biblioteka.models;

public class EditUserModel {

    private String email;
    private String newPassword;
    private String oldPassword;

    public EditUserModel(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
