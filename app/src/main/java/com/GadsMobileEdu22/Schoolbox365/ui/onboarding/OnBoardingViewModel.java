package com.GadsMobileEdu22.Schoolbox365.ui.onboarding;

import androidx.lifecycle.ViewModel;

import com.GadsMobileEdu22.Schoolbox365.R;

import java.util.ArrayList;

public class OnBoardingViewModel extends ViewModel {

    public ArrayList<OnboardingScreen> getOnboardScreens() {
        ArrayList<OnboardingScreen> screenList = new ArrayList<>(3);

        screenList.add(0, new OnboardingScreen(R.string.onboard_screen1_title, R.string.onboard_screen1_message,
                R.drawable.onboard_students, R.drawable.dot_indicator_filled, R.drawable.dot_indicator,
                R.drawable.dot_indicator));
        screenList.add(1, new OnboardingScreen(R.string.onboard_screen2_title, R.string.onboard_screen2_message,
                R.drawable.onboard_lecturers, R.drawable.dot_indicator, R.drawable.dot_indicator_filled,
                R.drawable.dot_indicator));
        screenList.add(2, new OnboardingScreen(R.string.onboard_screen3_title, R.string.onboard_screen3_message,
                R.drawable.onboard_admin, R.drawable.dot_indicator, R.drawable.dot_indicator,
                R.drawable.dot_indicator_filled));

        return screenList;
    }
}
