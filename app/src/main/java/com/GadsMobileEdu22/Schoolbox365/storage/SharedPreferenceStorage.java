package com.GadsMobileEdu22.Schoolbox365.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.GadsMobileEdu22.Schoolbox365.util.AppConstants;


/**
 * Created by ChukwuwaUchenna
 **/

public class SharedPreferenceStorage {

    private SharedPreferences mPreferences;

    public SharedPreferenceStorage(Context context) {
        mPreferences = context.getSharedPreferences(AppConstants.SHARED_PREF_KEY, Context.MODE_PRIVATE);
    }
}
