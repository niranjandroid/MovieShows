package com.niranjandroid.movieshows.data.prefs

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Niranjan P on 08/17/2017.
 */

class PreferencesHelper {
    companion object {
        var preferences: SharedPreferences ?= null

        fun init(context: Context, prefKey: String) {
            preferences = context.getSharedPreferences(prefKey, 0)
        }

        fun getBoolean(key: String): Boolean? {
            return preferences?.getBoolean(key, false)
        }

        fun getBoolean(key: String, defaultValue: Boolean): Boolean? {
            return preferences?.getBoolean(key, defaultValue)
        }

        fun saveBoolean(key: String, bool: Boolean) {
            preferences?.edit()?.putBoolean(key, bool)?.apply()
        }

        fun saveString(key: String, s: String) {
            preferences?.edit()?.putString(key, s)?.apply()
        }

        fun saveInteger(key: String, integer: Int?) {
            preferences?.edit()?.putInt(key, integer!!.toInt())?.apply()
        }

        fun clear() {
            preferences?.edit()?.clear()?.apply()
        }

        fun remove(key: String) {
            preferences?.edit()?.remove(key)?.apply()
        }

        fun getString(key: String): String? {
            return preferences?.getString(key, "")
        }

        fun getString(key: String, defVal: String): String? {
            return preferences?.getString(key, defVal)
        }

        @JvmOverloads fun getInteger(key: String, defVal: Int = 0): Int? {
            return preferences?.getInt(key, defVal)
        }

        fun clearSharedPrefs() {
            clear()
        }
    }
}
