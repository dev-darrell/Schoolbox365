package com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import timber.log.Timber;

public class    DashboardFragment extends Fragment
        implements DashboardRecyclerViewAdapter.OnDashBoardClickListener,
        NewsPagerAdapter.OnNewsClickListener {

    private DashboardViewModel mViewModel;
    private FragmentDashboardBinding mBinding;
    private List<NewsItem> mNewsItems;
    String name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentDashboardBinding.inflate(inflater);
        mViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        assert getArguments() != null;
        name = getArguments().getString("Name");
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String greet  = "Welcome Mr " + name;
        mBinding.welcomeUserTv.setText(greet);
        mBinding.dashboardRecyclervw.setLayoutManager(new GridLayoutManager(getContext(),
                2));

        mViewModel.dashboardItems.observe(getViewLifecycleOwner(), dashboardItems ->
                mBinding.dashboardRecyclervw.setAdapter(new DashboardRecyclerViewAdapter(dashboardItems, this)));

//        TODO: Ensure news items get shown in viewpager.

        mViewModel.newsItems.observe(getViewLifecycleOwner(), newsItems -> {
            Collections.reverse(mNewsItems);
            mNewsItems = newsItems;
            Timber.d("list of view pager %s", mNewsItems.get(1).toString());
            NewsPagerAdapter pagerAdapter = new NewsPagerAdapter(newsItems, this);
            pagerAdapter.notifyDataSetChanged();
            mBinding.newsVpager2.setAdapter(pagerAdapter);
        });
    }

    @Override
    public void onDashboardClick(int position) {
        switch (position) {
            case 0:
//              TODO: Open News/Announcements Activity or Fragment
                Bundle bundle = new Bundle();
                bundle.putString("NameString", name);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        NewsListFragment.class, bundle).commit();
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
