package com.peter.foody.data.utils

import android.annotation.SuppressLint
import android.content.Context
import android.preference.PreferenceManager
import java.lang.ref.WeakReference


class SharedPrefUtil {

    @SuppressLint("ApplySharedPref")
    fun save(context: Context?, key: String?, value: Any) {
        val contextWeakReference: WeakReference<Context> = WeakReference(context)
        val pref = PreferenceManager.getDefaultSharedPreferences(contextWeakReference.get())
        val editor = pref.edit()
        if (value is Int) {
            editor.putInt(key, value)
        } else if (value is Boolean) {
            editor.putBoolean(key, value)
        } else if (value is String) {
            editor.putString(key, value.toString())
        } else if (value is Float) {
            editor.putFloat(key, value)
        } else if (value is Long) {
            editor.putLong(key, value)
        }
        editor.apply()
    }


    fun getData(context: Context?, key: String?, defaultValue: Any): Any? {
        val contextWeakReference: WeakReference<Context> = WeakReference(context)
        val pref = PreferenceManager
            .getDefaultSharedPreferences(contextWeakReference.get())
        if (defaultValue is String) {
            return pref.getString(key, defaultValue.toString())
        } else if (defaultValue is Int) {
            return pref.getInt(key, defaultValue)
        } else if (defaultValue is Boolean) {
            return pref.getBoolean(key, defaultValue)
        } else if (defaultValue is Long) {
            return pref.getLong(key, defaultValue)
        } else if (defaultValue is Float) {
            return pref.getFloat(key, defaultValue)
        }
        return defaultValue
    }

    @SuppressLint("ApplySharedPref")
    fun remove(context: Context?, key: String?) {
        val contextWeakReference: WeakReference<Context> = WeakReference(context)
        val pref = PreferenceManager
            .getDefaultSharedPreferences(contextWeakReference.get())
        val editor = pref.edit()
        editor.remove(key).apply()
    }

    fun hasKey(context: Context?, key: String?): Boolean {
        val contextWeakReference: WeakReference<Context> = WeakReference(context)
        val pref = PreferenceManager.getDefaultSharedPreferences(contextWeakReference.get())
        //        Log.d("preffs", "hasKey: " + pref.getString(key, "no value"));
        return pref.contains(key)
    }


}