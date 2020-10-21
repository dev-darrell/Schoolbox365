package com.GadsMobileEdu22.Schoolbox365;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.GadsMobileEdu22.Schoolbox365.storage.SharedPreferenceStorage;
import com.GadsMobileEdu22.Schoolbox365.ui.onboarding.OnBoardingActivity;
import com.GadsMobileEdu22.Schoolbox365.ui.onboarding.OnboardingPagerAdapter;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String ONBOARDING_COMPLETE = "onboarding_complete";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new SharedPreferenceStorage(this);

        SharedPreferences preferences = SharedPreferenceStorage.getPreferences();

        if (!preferences.getBoolean(ONBOARDING_COMPLETE, false)) {
            Intent intent = new Intent(this, OnBoardingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);

            finish();
        }
    }
}