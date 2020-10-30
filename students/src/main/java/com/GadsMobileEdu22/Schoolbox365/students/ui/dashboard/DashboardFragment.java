package com.GadsMobileEdu22.Schoolbox365.students.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.GadsMobileEdu22.Schoolbox365.students.databinding.FragmentDashboardBinding;

import java.util.List;

public class DashboardFragment extends Fragment implements DashboardRecyclerViewAdapter.OnDashBoardClickListener,
        NewsPagerAdapter.OnNewsClickListener {

    private DashboardViewModel mViewModel;
    private FragmentDashboardBinding mBinding;
    private List<NewsItem> mNewsItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentDashboardBinding.inflate(inflater);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        mBinding.dashboardRecyclervw.setLayoutManager(new GridLayoutManager(getContext(),
                2));

        mViewModel.dashboardItems.observe(getViewLifecycleOwner(), dashboardItems -> {
            mBinding.dashboardRecyclervw.setAdapter(new DashboardRecyclerViewAdapter(getContext(),
                    dashboardItems, this));
        });
        mViewModel.newsItems.observe(getViewLifecycleOwner(), newsItems -> {
            mNewsItems = newsItems;
            mBinding.newsVpager2.setAdapter(new NewsPagerAdapter(getContext(),
                    newsItems, this));
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
        NewsItem currentItem = mNewsItems.get(position);
//        TODO: Open Activity/Fragment to display more information on selected news item.
    }
}