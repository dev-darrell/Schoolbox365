package com.GadsMobileEdu22.Schoolbox365.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.GadsMobileEdu22.Schoolbox365.R;
import com.GadsMobileEdu22.Schoolbox365.databinding.ActivityDashBoardBinding;

public class DashBoardActivity extends AppCompatActivity {

    private DashBoardViewModel viewModel;

    private ActivityDashBoardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = ActivityDashBoardBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(this).get(DashBoardViewModel.class);

        setContentView(binding.getRoot());
    }
}