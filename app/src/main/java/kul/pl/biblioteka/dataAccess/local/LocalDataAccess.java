package kul.pl.biblioteka.dataAccess.local;

import android.content.Context;
import android.content.SharedPreferences;

import kul.pl.biblioteka.secure.SecureToken;
import kul.pl.biblioteka.ui.activity.MainActivity;

import static kul.pl.biblioteka.utils.Constants.SP_LIBRARY;
import static kul.pl.biblioteka.utils.Constants.SP_LOGIN;
import static kul.pl.biblioteka.utils.Constants.SP_TOKEN;

public class LocalDataAccess {

    private static SharedPreferences getSP(){
       return MainActivity.getAppContext().getSharedPreferences(SP_LIBRARY, Context.MODE_PRIVATE);
    }

    public static boolean isLogin(){
        return getSP().getBoolean(SP_LOGIN, false);
    }

    public static void setLogin(boolean login){
        getSP().edit().putBoolean(SP_LOGIN, login).apply();
    }

    public static String getToken(){
        //return SecureToken.load(MainActivity.getAppContext());
        return getSP().getString(SP_TOKEN, "");
    }

    public static void setToken(String token){
       // SecureToken.save(token, MainActivity.getAppContext());
        getSP().edit().putString(SP_TOKEN, token).apply();
    }

    public static void clean(){
        SharedPreferences sp = getSP();
        sp.edit().remove(SP_TOKEN).apply();
        sp.edit().remove(SP_LOGIN).apply();
    }
}
