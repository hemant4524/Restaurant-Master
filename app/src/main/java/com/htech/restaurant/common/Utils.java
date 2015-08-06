package com.htech.restaurant.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
}
