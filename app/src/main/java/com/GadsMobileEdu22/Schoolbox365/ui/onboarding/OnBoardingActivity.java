package com.GadsMobileEdu22.Schoolbox365.ui.onboarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.GadsMobileEdu22.Schoolbox365.MainActivity;
import com.GadsMobileEdu22.Schoolbox365.databinding.ActivityOnboardingBinding;

public class OnBoardingActivity extends AppCompatActivity {

    private OnBoardingViewModel viewModel;
    private ActivityOnboardingBinding binding;
    public static ViewPager2 mViewPager;
    public static final String ONBOARDING_COMPLETE = "onboarding_complete";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(this).get(OnBoardingViewModel.class);
        mViewPager = binding.onboardingVpager;
        setContentView(binding.getRoot());

        SharedPreferences preferences = viewModel.getPreference(this);

        if (!preferences.getBoolean(ONBOARDING_COMPLETE, false)) {
            viewModel.onboardingScreenList.observe(this, onboardingScreens -> {
            OnboardingPagerAdapter adapter = new OnboardingPagerAdapter(this, onboardingScreens);
            mViewPager.setAdapter(adapter);
            });
        } else {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);
            finish();
        }
    }

}
