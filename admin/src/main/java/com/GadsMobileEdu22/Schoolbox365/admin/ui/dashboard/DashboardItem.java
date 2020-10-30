package com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard;

public class DashboardItem {
    private int itemIllustrationRes;
    private int itemText;

    public DashboardItem(int itemIllustrationRes, int itemText) {
        this.itemIllustrationRes = itemIllustrationRes;
        this.itemText = itemText;
    }

    public int getItemIllustrationRes() {
        return itemIllustrationRes;
    }

    public int getItemText() {
        return itemText;
    }
}
