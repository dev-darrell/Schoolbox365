package com.GadsMobileEdu22.Schoolbox365.ui.onboarding;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.GadsMobileEdu22.Schoolbox365.R;
import com.GadsMobileEdu22.Schoolbox365.storage.SharedPreferenceStorage;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingViewModel extends ViewModel {
    private MutableLiveData<List<OnboardingScreen>> screenList = new MutableLiveData<>();
    public LiveData<List<OnboardingScreen>> onboardingScreenList = screenList;


    public OnBoardingViewModel() {
        ArrayList<OnboardingScreen> screens = new ArrayList<>(3);
        screens.add(0, new OnboardingScreen(R.string.onboard_screen1_title, R.string.onboard_screen1_message,
                R.drawable.onboard_students, R.drawable.dot_indicator_filled, R.drawable.dot_indicator,
                R.drawable.dot_indicator));

        screens.add(1, new OnboardingScreen(R.string.onboard_screen2_title, R.string.onboard_screen2_message,
                R.drawable.onboard_lecturers, R.drawable.dot_indicator, R.drawable.dot_indicator_filled,
                R.drawable.dot_indicator));
        screens.add(2, new OnboardingScreen(R.string.onboard_screen3_title, R.string.onboard_screen3_message,
                R.drawable.onboard_admin, R.drawable.dot_indicator, R.drawable.dot_indicator,
                R.drawable.dot_indicator_filled));

        screenList.setValue(screens);
    }

    public SharedPreferences getPreference(Context context) {
        new SharedPreferenceStorage(context);
        return SharedPreferenceStorage.getPreferences();
    }
}
