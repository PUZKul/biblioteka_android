package kul.pl.biblioteka.models;

public class EditUserModel {

    private String email;
    private String newPassword;
    private String oldPassword;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;


    public EditUserModel(String email, String newPassword, String oldPassword) {
        this.email = email;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public EditUserModel(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
