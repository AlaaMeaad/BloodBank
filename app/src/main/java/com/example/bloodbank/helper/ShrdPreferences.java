package com.example.bloodbank.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class ShrdPreferences {
    private static SharedPreferences sharedPreferences = null;

    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "BloodRefrences", activity.MODE_PRIVATE);
        }
    }

    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }

    public static void SaveData(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        }
    }

    public static void SaveData(Activity activity, String data_Key, int data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(data_Key, data_Value);
            editor.commit();
        }
    }

    public static void SaveData(Activity activity, String data_Key, boolean data_Value) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.apply();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static String LoadStringData(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        }
        return sharedPreferences.getString(data_Key, null);
    }

    public static Integer LoadIntegerData(Activity activity, String data_Key) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }
        return sharedPreferences.getInt(data_Key, 0);
    }

    public static boolean LoadBoolean(Activity activity, String data_Key, boolean isCheckBox) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }
        return sharedPreferences.getBoolean(data_Key, false);
    }

    public static void intent(Context context, Class<?> cls) {
        Intent in = new Intent(context, cls);
        context.startActivity(in);
    }
}
