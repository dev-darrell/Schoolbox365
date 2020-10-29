package com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.GadsMobileEdu22.School365.admin.ui.dashboard.DashboardFragment;
import com.GadsMobileEdu22.School365.admin.ui.dashboard.MenuFragment;
import com.GadsMobileEdu22.School365.admin.ui.dashboard.NewsItem;
import com.GadsMobileEdu22.School365.admin.ui.dashboard.SearchFragment;
import com.GadsMobileEdu22.Schoolbox365.admin.R;
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.ActivityAdminDashBoardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class AdminDashBoardActivity extends AppCompatActivity {

    private List<NewsItem> newsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdminDashBoardBinding binding = ActivityAdminDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                DashboardFragment.class, null).commit();
        binding.bottomNavView.setSelectedItemId(R.id.nav_home);
        binding.bottomNavView.setOnNavigationItemSelectedListener(navListener);
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