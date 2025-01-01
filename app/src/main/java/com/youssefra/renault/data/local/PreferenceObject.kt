package com.youssefra.renault.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

const val PREFERENCE_FILE_NAME = "TIME_KEY"

@Singleton
class PreferenceObject @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)

    // put data
    fun putString(key: String, value: String = "") {
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun putBoolean(key: String, value: Boolean = false) {
        with(sharedPref.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    fun putInt(key: String, value: Int = 0) {
        with(sharedPref.edit()) {
            putInt(key, value)
            apply()
        }
    }
    fun putLong(key: String, value: Long = 0) {
        with(sharedPref.edit()) {
            putLong(key, value)
            apply()
        }
    }

    // get data
    fun getString(key: String): String {
        return sharedPref.getString(key, "") ?: ""
    }

    fun getInt(key: String): Int {
        return sharedPref.getInt(key, -1)
    }
    fun getLong(key: String): Long {
        return sharedPref.getLong(key, 0)
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }



}