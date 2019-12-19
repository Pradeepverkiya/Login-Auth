package com.pradeep.loginauth.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Vector;

public class CommonMethod {

    private static SharedPreferences.Editor editor;

    public static void setPrefsData(Context context, String prefsKey, String prefValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putString(prefsKey, prefValue);
        editor.apply();
    }

    public static String getPrefsData(Context context, String prefsKey, String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(prefsKey, defaultValue);
    }
}



