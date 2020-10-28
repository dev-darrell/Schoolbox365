package com.GadsMobileEdu22.School365.admin.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.GadsMobileEdu22.School365.admin.R;
import com.GadsMobileEdu22.School365.admin.databinding.ActivityAdminDashboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class AdminDashBoardActivity extends AppCompatActivity {

    private AdminViewModel viewModel;
    private List<NewsItem> newsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdminDashboardBinding binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
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