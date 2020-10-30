package com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.GadsMobileEdu22.Schoolbox365.admin.R;
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.FragmentDashboardBinding;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.adapters.DashboardRecyclerViewAdapter;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.adapters.NewsPagerAdapter;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.announcements.NewsListFragment;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.storage.UploadResourceActivity;

import java.util.List;

public class    DashboardFragment extends Fragment
        implements DashboardRecyclerViewAdapter.OnDashBoardClickListener,
        NewsPagerAdapter.OnNewsClickListener {

    private DashboardViewModel mViewModel;
    private FragmentDashboardBinding mBinding;
    private List<NewsItem> mNewsItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentDashboardBinding.inflate(inflater);
        mViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.dashboardRecyclervw.setLayoutManager(new GridLayoutManager(getContext(),
                2));

        mViewModel.dashboardItems.observe(getViewLifecycleOwner(), dashboardItems -> mBinding.dashboardRecyclervw.setAdapter(new DashboardRecyclerViewAdapter(dashboardItems, this)));
        mViewModel.newsItems.observe(getViewLifecycleOwner(), newsItems -> {
            mNewsItems = newsItems;
            mBinding.newsVpager2.setAdapter(new NewsPagerAdapter(newsItems, this));
        });
    }

    @Override
    public void onDashboardClick(int position) {
        switch (position) {
            case 0:
//              TODO: Open News/Announcements Activity or Fragment
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        NewsListFragment.class, null).commit();
                break;
            case 1:
//              TODO: Open Users Activity/Fragment
                break;
            case 2:
//              TODO: Open Upload Resources Activity/Fragment
                Intent intent = new Intent(getContext(), UploadResourceActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onNewsClick(int position) {
        NewsItem currentItem = mNewsItems.get(position);
//        TODO: Open Activity/Fragment to display more information on selected news item.
    }
}
