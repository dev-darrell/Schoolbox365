package com.GadsMobileEdu22.Schoolbox365.ui.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.database.DatabaseUtils;
import android.os.Bundle;

import com.GadsMobileEdu22.Schoolbox365.R;
import com.GadsMobileEdu22.Schoolbox365.databinding.ActivityOnboardingBinding;

public class OnBoardingActivity extends AppCompatActivity {

    private OnBoardingViewModel viewModel;
    private ActivityOnboardingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(OnBoardingViewModel.class);

        setContentView(binding.getRoot());


    }
}