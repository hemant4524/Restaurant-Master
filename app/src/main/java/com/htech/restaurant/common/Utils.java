package com.htech.restaurant.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by software on 8/6/15.
 */
public class Utils {

    /**
     * Save value in shared preferences
     *
     */
    public static void saveValueInPreferences(Context context,String key,String value)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    /**
     * Read value from shared preferences
     */
    public static String readValueFromPreferences(Context context,String key)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = preferences.getString(key, "");
        return value;
    }

    /**
     * Find current date time
     */
    public static String getCurrentDateTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
