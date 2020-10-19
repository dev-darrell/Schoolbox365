package com.GadsMobileEdu22.Schoolbox365;

import android.app.Application;

import androidx.viewbinding.BuildConfig;

import com.GadsMobileEdu22.Schoolbox365.storage.SharedPreferenceStorage;

import timber.log.Timber;

/**
 * Created by ChukwuwaUchenna
 */
/*
 * This is not the AuthActivity class
 *  This class is for Logging with Timber
 * and persistence logic
 **/

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Init sharedPreference
        new SharedPreferenceStorage(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseTree());
        }
    }
}
