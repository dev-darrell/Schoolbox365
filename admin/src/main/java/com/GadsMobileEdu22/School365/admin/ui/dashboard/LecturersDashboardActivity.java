package com.GadsMobileEdu22.School365.admin.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.GadsMobileEdu22.School365.admin.R;

public class LecturersDashboardActivity extends AppCompatActivity {

    private LecturesViewModel viewModel;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturers_dashboard);
    }
}