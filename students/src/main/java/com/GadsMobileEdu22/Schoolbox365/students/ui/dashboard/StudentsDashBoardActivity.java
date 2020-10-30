package com.GadsMobileEdu22.Schoolbox365.students.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.GadsMobileEdu22.Schoolbox365.students.R;
import com.GadsMobileEdu22.Schoolbox365.students.databinding.ActivityStudentsDashBoardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentsDashBoardActivity extends AppCompatActivity {


    private ActivityStudentsDashBoardBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityStudentsDashBoardBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                DashboardFragment.class, null).commit();
        mBinding.bottomNavView.setSelectedItemId(R.id.nav_home);
        mBinding.bottomNavView.setOnNavigationItemSelectedListener(navListener);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        if (item.getItemId() == R.id.nav_search) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, SearchFragment.class, null)
                    .commit();
        } else if (item.getItemId() == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, DashboardFragment.class, null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, MenuFragment.class, null)
                    .commit();
        }
        return true;
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.select_course_menu_item){
//            TODO: Add functionality for switching courses.
        }
        return super.onOptionsItemSelected(item);
    }
}