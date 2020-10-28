package com.GadsMobileEdu22.School365.admin.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.GadsMobileEdu22.School365.admin.R;
import com.GadsMobileEdu22.School365.admin.databinding.ActivityAdminDashboardBinding;

import java.util.List;

public class AdminDashBoardActivity extends AppCompatActivity
        implements DashboardRecyclerViewAdapter.OnDashBoardClickListener, NewsPagerAdapter.OnNewsClickListener {

    private AdminViewModel viewModel;
    private List<NewsItem> newsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdminDashboardBinding binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        RecyclerView dashboardRecyclerV = binding.dashboardRecyclervw;
        ViewPager2 newsVpager2 = binding.newsVpager2;
        viewModel = new AdminViewModel();

        dashboardRecyclerV.setLayoutManager(new GridLayoutManager(this, 2));

        viewModel.dashboardItems.observe(this, dashboardItems -> {
            dashboardRecyclerV.setAdapter(new DashboardRecyclerViewAdapter(this, dashboardItems, this));
        });

        viewModel.newsItems.observe(this, newsItems -> {
            this.newsItems = newsItems;
            newsVpager2.setAdapter(new NewsPagerAdapter(this, newsItems, this));
        });
    }

    @Override
    public void onDashboardClick(int position) {
        switch (position) {
            case 0:
//              TODO: Open News/Announcements Activity or Fragment
                break;
            case 1:
//              TODO: Open Users Activity/Fragment
                break;
            case 2:
//              TODO: Open Upload Resources Activity/Fragment
                break;
        }
    }

    @Override
    public void onNewsClick(int position) {
        NewsItem currentItem = newsItems.get(position);
//        TODO: Open Activity/Fragment displaying more information on selected news item.
    }
}