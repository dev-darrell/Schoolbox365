package com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.GadsMobileEdu22.Schoolbox365.admin.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends ViewModel {
    private final MutableLiveData<List<DashboardItem>> _dashList = new MutableLiveData<>();
    private final MutableLiveData<List<NewsItem>> _newsList = new MutableLiveData<>();
    public LiveData<List<DashboardItem>> dashboardItems = _dashList;
    public LiveData<List<NewsItem>> newsItems = _newsList;

    public DashboardViewModel() {
        _dashList.setValue(addDashboardItems());

        _newsList.setValue(addNewsItems());
    }

    private List<NewsItem> addNewsItems() {
        ArrayList<NewsItem> items = new ArrayList<>();
//        TODO: Add news items received from firebase to the 'items' ArrayList.
//        Item id represents which dot will be selected. Only 5 dots are available (i.e. IDs 1-5)
        items.add(new NewsItem(1, R.drawable.onboard_admin, "Dummy News Item",
                "Further dummy text."));
        items.add(new NewsItem(2, R.drawable.onboard_admin, "Second Dummy News Item",
                "Further dummy text."));

        return items;
    }

    private ArrayList<DashboardItem> addDashboardItems() {
        ArrayList<DashboardItem> items = new ArrayList<>();
        items.add(0, new DashboardItem(R.drawable.ic_notifications, R.string.dashboard_send_announcement));
        items.add(1, new DashboardItem(R.drawable.ic_users, R.string.dashboard_card_users));
        items.add(2, new DashboardItem(R.drawable.ic_resources, R.string.dashboard_card_resources));
        return items;
    }
}
