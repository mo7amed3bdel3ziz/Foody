package com.peter.foody.framework.presentation.main.reports.utilShared

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesBillStatu {
    private lateinit var Instance: SharedPreferencesBillStatu
    private lateinit var sharedPreferencesLogIn: SharedPreferences
    private  lateinit var edits: SharedPreferences.Editor

    fun SharedPreferencesBillStatu() {}

  //  fun init(context: Context) {
  //      Instance = SharedPreferencesBillStatu(context)
  //  }
//
  //  fun getInstance(): SharedPreferencesBillStatu? {
  //      if (null == Instance) Instance = SharedPreferencesBillStatu()
  //      return Instance
  //  }

    fun SharedPreferencesBillStatu(context: Context) {
        sharedPreferencesLogIn = context.getSharedPreferences("NumberBill", Context.MODE_PRIVATE)
        edits = sharedPreferencesLogIn.edit()
    }


    fun getNumberOFBill(): String? {
        val Number = sharedPreferencesLogIn!!.getString("NumberOFBill", "30")
        //   Toast.makeText(context, Number, Toast.LENGTH_SHORT).show();
        val newNumber = Integer.valueOf(Number) + 1
        //ToPost
        edits!!.putString("NumberOFBill", newNumber.toString())
        edits!!.apply()
        return newNumber.toString()

        // return Number;
    }
}