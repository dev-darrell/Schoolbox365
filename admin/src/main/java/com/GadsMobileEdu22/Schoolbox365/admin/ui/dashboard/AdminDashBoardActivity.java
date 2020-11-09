package com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.GadsMobileEdu22.Schoolbox365.admin.R;
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.ActivityAdminDashBoardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminDashBoardActivity extends AppCompatActivity {

//    private List<NewsItem> newsItems;

    private  String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdminDashBoardBinding binding = ActivityAdminDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DashboardViewModel viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

//        TODO: Work on passing username to Dashboard Fragment using SafeArgs

        if (getIntent().hasExtra("usersName")) {
            name = getIntent().getStringExtra("usersName");
        } else {
            name = "";
        }
        viewModel.setUserName(name);

        binding.toolbar.getOverflowIcon().setTint(getResources().getColor(R.color.background_color_light));
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setTitle("Welcome Mr " + name);

        binding.bottomNavView.setSelectedItemId(R.id.nav_home);
        binding.bottomNavView.setOnNavigationItemSelectedListener(navListener);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        if (item.getItemId() == R.id.nav_search) {
            DashboardFragment.openSearch();
//            getSupportFragmentManager().beginTransaction().replace(
//                    R.id.nav_host_fragment, SearchFragment.class, null)
//                    .commit();
        } else if (item.getItemId() == R.id.nav_home) {
            DashboardFragment.openHome();
//                getSupportFragmentManager().beginTransaction().replace(
//                        R.id.nav_host_fragment, DashboardFragment.class, mDashboardBundle)
//                        .commit();
        } else {
            DashboardFragment.openMenu();
//            getSupportFragmentManager().beginTransaction().replace(
//                    R.id.nav_host_fragment, MenuFragment.class, null)
//                    .commit();
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
            DashboardFragment.openNewUser();
//           getSupportFragmentManager().beginTransaction().replace(
//                    R.id.nav_host_fragment, RegisterNewUserFragment.class, null)
//                    .commit();
           return true;
        }
        return super.onOptionsItemSelected(item);
    }
}