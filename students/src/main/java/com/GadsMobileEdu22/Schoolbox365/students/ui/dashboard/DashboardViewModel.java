package com.GadsMobileEdu22.Schoolbox365.students.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.GadsMobileEdu22.Schoolbox365.students.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends ViewModel {
    private MutableLiveData<List<DashboardItem>> _dashList = new MutableLiveData<>();
    private MutableLiveData<List<NewsItem>> _newsList = new MutableLiveData<>();
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
        items.add(new NewsItem(1, R.drawable.illus_assignment, "Dummy News Item",
                "Further dummy text."));
        items.add(new NewsItem(2, R.drawable.illus_assignment, "Second Dummy News Item",
                "Further dummy text."));

        return items;
    }

    private ArrayList<DashboardItem> addDashboardItems() {
        ArrayList<DashboardItem> items = new ArrayList<>();
        items.add(0, new DashboardItem(R.drawable.illus_lectures, R.string.dashboard_card_lectures));
        items.add(1, new DashboardItem(R.drawable.illus_assignment, R.string.dashboard_card_assignments));
        items.add(2, new DashboardItem(R.drawable.illus_resources, R.string.dashboard_card_resources));
        return items;
    }
}
