package kul.pl.biblioteka.dataAccess.local;

import android.content.Context;
import android.content.SharedPreferences;

import kul.pl.biblioteka.secure.SecureToken;
import kul.pl.biblioteka.ui.activity.MainActivity;

import static kul.pl.biblioteka.utils.Constants.SP_LIBRARY;
import static kul.pl.biblioteka.utils.Constants.SP_LOGIN;

public class LocalDataAccess {

    public static boolean isLogin(){
        SharedPreferences sp = MainActivity.getAppContext()
                .getSharedPreferences(SP_LIBRARY, Context.MODE_PRIVATE);

        return sp.getBoolean(SP_LOGIN, false);
    }

    public static void setLogin(boolean login){
        SharedPreferences sp = MainActivity.getAppContext()
                .getSharedPreferences(SP_LIBRARY, Context.MODE_PRIVATE);

        sp.edit().putBoolean(SP_LOGIN, login).apply();
    }

    public static String getToken(){
        return SecureToken.load(MainActivity.getAppContext());
    }

    public static void setToken(String token){
        SecureToken.save(token, MainActivity.getAppContext());
    }
}
