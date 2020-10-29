package com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                DashboardFragment.class, null).commit();
        binding.bottomNavView.setSelectedItemId(R.id.nav_home);
        binding.bottomNavView.setOnNavigationItemSelectedListener(navListener);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        switch (item.getItemId()){
            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.fragment_container, SearchFragment.class, null)
                .commit();
            break;
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.fragment_container, DashboardFragment.class, null)
                        .commit();
                break;
            case R.id.nav_menu:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.fragment_container, MenuFragment.class, null)
                        .commit();
                break;
        }
        return true;
    };
}