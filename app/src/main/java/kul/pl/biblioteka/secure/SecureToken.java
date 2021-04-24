package kul.pl.biblioteka.secure;

import android.content.Context;
import android.content.SharedPreferences;

import static kul.pl.biblioteka.utils.Constants.SP_LIBRARY;
import static kul.pl.biblioteka.utils.Constants.SP_TOKEN;

public class SecureToken {
    private static final String ALIAS = "RwM@4R4UQq8pWGQ9jCp*V1VoBIRNnUsIlj";

    public static void save(String information, Context ctx){
        String encryptedToken = AESCipher.encrypt(information, ALIAS);
        SharedPreferences sp = ctx.getSharedPreferences(SP_LIBRARY, Context.MODE_PRIVATE);
        sp.edit().putString(SP_TOKEN, encryptedToken).apply();
    }


    public static String load(Context ctx){
        String encryptedToken = ctx.getSharedPreferences(SP_LIBRARY, Context.MODE_PRIVATE).getString(SP_TOKEN, "");
        return AESCipher.decrypt(encryptedToken, ALIAS);
    }

}
