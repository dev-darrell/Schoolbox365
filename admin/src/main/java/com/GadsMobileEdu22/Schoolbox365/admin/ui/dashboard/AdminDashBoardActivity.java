package com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.GadsMobileEdu22.Schoolbox365.admin.R;
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.ActivityAdminDashBoardBinding;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.auth.RegisterNewUserFragment;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.menu.MenuFragment;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminDashBoardActivity extends AppCompatActivity {

//    private List<NewsItem> newsItems;

    private final String name = getIntent().getStringExtra("usersName");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdminDashBoardBinding binding = ActivityAdminDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("welcome Mr " + name);

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
            Bundle bundle = new Bundle();
            bundle.putString("Name", name);
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.fragment_container, DashboardFragment.class, bundle)
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
           getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, RegisterNewUserFragment.class, null)
                    .commit();
           return true;
        }
        return super.onOptionsItemSelected(item);
    }
}