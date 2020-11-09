package com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.GadsMobileEdu22.Schoolbox365.admin.R;
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.FragmentDashboardBinding;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.adapters.DashboardRecyclerViewAdapter;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.adapters.NewsPagerAdapter;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.menu.MenuFragmentDirections;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.search.SearchFragmentDirections;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import om.GadsMobileEdu22.Schoolbox365.core.data.News;
import timber.log.Timber;

public class    DashboardFragment extends Fragment
        implements DashboardRecyclerViewAdapter.OnDashBoardClickListener,
        NewsPagerAdapter.OnNewsClickListener {

    private DashboardViewModel mViewModel;
    private static FragmentDashboardBinding mBinding;
    private List<NewsItem> mNewsItems;
    private String name = "";
    private final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("development/news");
    private final MutableLiveData<List<NewsItem>> _newsList = new MutableLiveData<>();
    public LiveData<List<NewsItem>> newsItems = _newsList;;
    private NewsPagerAdapter mPagerAdapter;
    private static NavController mNavController;

    public static void openHome() {
        if (mNavController.getCurrentDestination().getId() != R.id.dashboardFragment) {
            mNavController.navigate(R.id.dashboardFragment);
        }
    }

    public static void openSearch() {
        if (mNavController.getCurrentDestination().getId() != R.id.searchFragment) {
            if (mNavController.getCurrentDestination().getId() == R.id.dashboardFragment) {
                NavDirections directions = DashboardFragmentDirections.actionDashboardFragmentToSearchFragment();
                mNavController.navigate(directions);
            } else if (mNavController.getCurrentDestination().getId() == R.id.menuFragment){
                NavDirections directions = MenuFragmentDirections.actionMenuFragmentToSearchFragment();
                mNavController.navigate(directions);
            }
        }
    }

    public static void openMenu() {
        if (mNavController.getCurrentDestination().getId() != R.id.menuFragment) {
            if (mNavController.getCurrentDestination().getId() == R.id.dashboardFragment) {
                NavDirections directions = DashboardFragmentDirections.actionDashboardFragmentToMenuFragment();
                mNavController.navigate(directions);
            } else if (mNavController.getCurrentDestination().getId() == R.id.searchFragment){
                NavDirections directions = SearchFragmentDirections.actionSearchFragmentToMenuFragment();
                mNavController.navigate(directions);
            }
        }
    }

    public static void openNewUser() {
        NavDirections directions = DashboardFragmentDirections.actionDashboardFragmentToRegisterNewUserFragment();
        mNavController.navigate(directions);
    }


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

        mViewModel.dashboardItems.observe(getViewLifecycleOwner(), dashboardItems ->
                mBinding.dashboardRecyclervw.setAdapter(new DashboardRecyclerViewAdapter(dashboardItems, this)));

        mNavController = Navigation.findNavController(view);

        getNewsFromDb();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel.getUserName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                name = s;
            }
        });
        String greet  = "Welcome Mr " + name;
        mBinding.welcomeUserTv.setText(greet);
    }

    private void getNewsFromDb() {
        List<News> items = new ArrayList<>();
        List<NewsItem> newsItems = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    News news = dataSnapshot.getValue(News.class);
                    if (news != null){
                        items.add(news);
                    }
                }

                Collections.reverse(items);

                for (News item : items){
                    NewsItem newsItem = new NewsItem(items.indexOf(item) + 1, item.getImage(), item.getTittle(),item.getDescription());
                    if (newsItems.size() <= 4){
                        newsItems.add(newsItem);
                    }else {
                        return;
                    }
                    if (newsItems.size() > 0){
                        _newsList.setValue(newsItems);
                        mNewsItems = newsItems;
                        mPagerAdapter = new NewsPagerAdapter(mNewsItems, DashboardFragment.this::onNewsClick);
                        mPagerAdapter.notifyDataSetChanged();
                        mBinding.newsVpager2.setAdapter(mPagerAdapter);
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Timber.d("database error %s", error.getMessage());
            }
        });
    }

    @Override
    public void onDashboardClick(int position) {
        switch (position) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putString("NameString", name);

                NavDirections action = DashboardFragmentDirections.actionDashboardFragmentToNewsListFragment();
                mNavController.navigate(action);
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
