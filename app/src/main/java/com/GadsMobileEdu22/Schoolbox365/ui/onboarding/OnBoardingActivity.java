package com.GadsMobileEdu22.Schoolbox365.ui.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.GadsMobileEdu22.Schoolbox365.MainActivity;
import com.GadsMobileEdu22.Schoolbox365.R;
import com.GadsMobileEdu22.Schoolbox365.databinding.ActivityOnboardingBinding;
import com.GadsMobileEdu22.Schoolbox365.databinding.OnboardingScreenLayoutBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {

    private OnBoardingViewModel viewModel;
    private ActivityOnboardingBinding binding;
    public static ViewPager2 mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(OnBoardingViewModel.class);
        mViewPager = binding.onboardingVpager;
        setContentView(binding.getRoot());


        ArrayList<OnboardingScreen> onboardingScreens = viewModel.getOnboardScreens();
        OnboardingPagerAdapter adapter = new OnboardingPagerAdapter(this, onboardingScreens);

        mViewPager.setAdapter(adapter);

    }

}
