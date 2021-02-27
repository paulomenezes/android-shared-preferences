package com.paulomenezes.filestorage02.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPreferenceUtils(context: Context) {
    companion object {
        const val SHARED_PREFERENCE_FILE = "shared-preference"
        const val SHARED_PREFERENCE_KEY_NAME = "name"
        const val SHARED_PREFERENCE_KEY_AGE = "age"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE)
    private val defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun write(name: String, age: Int) {
        sharedPreferences.edit()
            .putString(SHARED_PREFERENCE_KEY_NAME, name)
            .putInt(SHARED_PREFERENCE_KEY_AGE, age)
            .apply()
    }

    fun read(): Pair<String?, Int> {
        val name = sharedPreferences.getString(SHARED_PREFERENCE_KEY_NAME, null)
        val age = sharedPreferences.getInt(SHARED_PREFERENCE_KEY_AGE, 0)

        return Pair(name, age)
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}