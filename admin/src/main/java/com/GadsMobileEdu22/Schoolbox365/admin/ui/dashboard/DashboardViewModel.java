package com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.GadsMobileEdu22.Schoolbox365.admin.R;
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

public class DashboardViewModel extends ViewModel {
    private final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("development/news");
    private final MutableLiveData<List<DashboardItem>> _dashList = new MutableLiveData<>();
    private final MutableLiveData<List<NewsItem>> _newsList = new MutableLiveData<>();
    private final MutableLiveData<String> _userName = new MutableLiveData<>();
    public LiveData<List<DashboardItem>> dashboardItems = _dashList;
    public LiveData<List<NewsItem>> newsItems = _newsList;
    private LiveData<String> userName = _userName;

    public DashboardViewModel() {
        _dashList.setValue(addDashboardItems());
//        addNewsItems();
    }

    public void setUserName(String userName){
        _userName.setValue(userName);
    }

    public LiveData<String> getUserName() {
        return userName;
    }

    private void addNewsItems() {
//        TODO: Ensure news items get shown in viewpager.
        List<News> items = new ArrayList<>();
        List<NewsItem> newsItems = new ArrayList<>();
        newsItems.add(new NewsItem(1, String.valueOf(R.drawable.ic_notifications),
                "Loading News", ""));
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
                }
                _newsList.setValue(newsItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Timber.d("database error %s", error.getMessage());
            }
        });
        _newsList.setValue(newsItems);
    }

    private ArrayList<DashboardItem> addDashboardItems() {
        ArrayList<DashboardItem> items = new ArrayList<>();
        items.add(0, new DashboardItem(R.drawable.ic_notifications, R.string.dashboard_send_announcement));
        items.add(1, new DashboardItem(R.drawable.ic_users, R.string.dashboard_card_users));
        items.add(2, new DashboardItem(R.drawable.ic_resources, R.string.dashboard_card_resources));
        return items;
    }
}
