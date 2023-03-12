package com.peter.foody.framework.presentation.main.reports.utilShared;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesBillStatu {

    private static SharedPreferencesBillStatu Instance;
    private SharedPreferences sharedPreferencesLogIn;
    private SharedPreferences.Editor edits;

    public SharedPreferencesBillStatu() {
    }

    public static void init(Context context) {
        Instance = new SharedPreferencesBillStatu(context);

    }

    public static SharedPreferencesBillStatu getInstance() {
        if (null == Instance)
            Instance = new SharedPreferencesBillStatu();
        return Instance;
    }

    public SharedPreferencesBillStatu(Context context) {

        sharedPreferencesLogIn = context.getSharedPreferences("NumberBill", MODE_PRIVATE);
        edits = sharedPreferencesLogIn.edit();


    }


    public String getNumberOFBill() {
        String Number = sharedPreferencesLogIn.getString("NumberOFBill", "500");
        //   Toast.makeText(context, Number, Toast.LENGTH_SHORT).show();
        int newNumber = Integer.valueOf(Number) + 1;
        //ToPost
        edits.putString("NumberOFBill", String.valueOf(newNumber));
        edits.apply();
        return String.valueOf(newNumber);

       // return Number;
    }
}
