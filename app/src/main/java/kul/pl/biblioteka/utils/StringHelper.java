package kul.pl.biblioteka.utils;

public class StringHelper {

    public static boolean validateNickLogin(String nick) {
        return nick.length() > 3;
    }

    public static boolean validatePasswordLogin(String password) {
        return password.length() > 5;
    }

    public static boolean validatePasswordRegistration(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,18}$");
    }

    public static boolean validateTwoPasswordRegistration(String password1, String password2) {
        return password1.equals(password2);
    }

    public static boolean validateLoginRegistration(String login) {
        return login.matches("^[a-zA-Z0-9]{3,12}+$");
    }

    public static boolean validateEmailRegistration(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }
}
