package com.GadsMobileEdu22.Schoolbox365.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.GadsMobileEdu22.Schoolbox365.R;

public class DashBoardActivity extends AppCompatActivity {

    private DashBoardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        viewModel = new ViewModelProvider(this).get(DashBoardViewModel.class);
    }
}