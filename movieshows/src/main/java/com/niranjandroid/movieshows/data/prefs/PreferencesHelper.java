package com.niranjandroid.movieshows.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Niranjan P on 08/17/2017.
 */

public class PreferencesHelper {
    public static SharedPreferences preferences;

    public PreferencesHelper() {
    }

    public static void init(Context context, String prefKey) {
        preferences = context.getSharedPreferences(prefKey, 0);
    }

    public static boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public static void saveBoolean(String key, boolean bool) {
        preferences.edit().putBoolean(key, bool).apply();
    }

    public static void saveString(String key, String s) {
        preferences.edit().putString(key, s).apply();
    }

    public static void saveInteger(String key, Integer integer) {
        preferences.edit().putInt(key, integer.intValue()).apply();
    }

    public static void clear() {
        preferences.edit().clear().apply();
    }

    public static void remove(String key) {
        preferences.edit().remove(key).apply();
    }

    public static String getString(String key) {
        return preferences.getString(key, "");
    }

    public static String getString(String key, String defVal) {
        return preferences.getString(key, defVal);
    }

    public static int getInteger(String key) {
        return getInteger(key, 0);
    }

    public static int getInteger(String key, int defVal) {
        return preferences.getInt(key, defVal);
    }

    public static void clearSharedPrefs() {
        clear();
    }
}
