package com.example.weatherapp.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String WHEATHER_DATA = "WHEATHER_DATA";


    // properties
    private static final String LAST_SEARCH_TEXT = "LAST_SEARCH_TEXT";
    // other properties...


    private SharedPreferencesManager() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(WHEATHER_DATA, Context.MODE_PRIVATE);
    }

    public static String getLastSearchText(Context context) {
        return getSharedPreferences(context).getString(LAST_SEARCH_TEXT , null);
    }

    public static void setLastSearchText(Context context, String newValue) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LAST_SEARCH_TEXT , newValue);
        editor.commit();
    }
}
