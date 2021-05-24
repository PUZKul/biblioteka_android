package kul.pl.biblioteka.exception;

import kul.pl.biblioteka.secure.LogoutHandler;

public class ApiExceptionHandler {

    public static void handle(ApiError error){
        if (error.getStatus() == 401 && error.getMessage().equals("Token is invalid")){
            LogoutHandler.saveLogout("Expired credentials. Please login again");
        }
    }
}
