package com.example.tsangjunwen.gpacalculator.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tsangjunwen on 11/5/15.
 */
public class PreferenceStore {

    static final String TAG = "PreferencesHelper";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public static final String DEFAULT_PREFERENCE = "USER_PREF";
    private static final String USER_GPA = "USER_GPA";
    private static final String USER_CREDITS = "USER_CREDITS";

    public PreferenceStore(Context context) {
        mSharedPreferences = context.getSharedPreferences(DEFAULT_PREFERENCE, Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public boolean storeGPA (double gpa){
        mEditor.remove(USER_GPA);
        mEditor.putString(USER_GPA, Double.toString(gpa));
        return mEditor.commit();
    }

    public double getGPA(){
        String gpa = mSharedPreferences.getString(USER_GPA,"0.0");
        return Double.parseDouble(gpa);
    }

    public boolean storeModuleCredits(int mc){
        mEditor.remove(USER_CREDITS);
        mEditor.putInt(USER_CREDITS, mc);
        return mEditor.commit();
    }

    public int getModuleCredits(){
        return mSharedPreferences.getInt(USER_CREDITS, 0);
    }
}
