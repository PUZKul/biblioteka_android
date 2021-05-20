package kul.pl.biblioteka.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Helper {
    public static String getDefaultDateFormat(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public static String getShortDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd", Locale.ENGLISH);
        return dateFormat.format(date);
    }
}
