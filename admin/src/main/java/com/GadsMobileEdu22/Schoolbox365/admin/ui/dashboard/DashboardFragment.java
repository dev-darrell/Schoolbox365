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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;

import com.GadsMobileEdu22.Schoolbox365.admin.R;
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.FragmentDashboardBinding;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.adapters.DashboardRecyclerViewAdapter;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.adapters.NewsPagerAdapter;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.announcements.NewsListFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import om.GadsMobileEdu22.Schoolbox365.core.data.News;
import timber.log.Timber;

public class    DashboardFragment extends Fragment
        implements DashboardRecyclerViewAdapter.OnDashBoardClickListener,
        NewsPagerAdapter.OnNewsClickListener {

    private DashboardViewModel mViewModel;
    private FragmentDashboardBinding mBinding;
    private List<NewsItem> mNewsItems;
    String name = "";
    private final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("development/news");
    private final MutableLiveData<List<NewsItem>> _newsList = new MutableLiveData<>();
    public LiveData<List<NewsItem>> newsItems = _newsList;;
    private NewsPagerAdapter mPagerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentDashboardBinding.inflate(inflater);
        mViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        if(getArguments() != null){
            name = getArguments().getString("Name");
        }
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

        getNewsFromDb();

//        this.newsItems.observe(getViewLifecycleOwner(), newsItems1 -> {
//            Collections.reverse(mNewsItems);
////            Timber.d("list of view pager %s", mNewsItems.get(0).toString());
//            mPagerAdapter = new NewsPagerAdapter(mNewsItems, this);
//            mPagerAdapter.notifyDataSetChanged();
//            mBinding.newsVpager2.setAdapter(mPagerAdapter);
//        });
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
//        _newsList.setValue(newsItems);
    }

    @Override
    public void onDashboardClick(int position) {
        switch (position) {
            case 0:
//              TODO: Open News/Announcements Activity or Fragment
                Bundle bundle = new Bundle();
                bundle.putString("NameString", name);

//                Navigation.findNavController(mBinding.getRoot()).navigate( );
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
