package kul.pl.biblioteka.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Helper {
    public static String getDefaultDateFormat(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
        return dateFormat.format(date);
    }
}