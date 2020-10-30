package com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard;

import com.GadsMobileEdu22.Schoolbox365.admin.R;

public class NewsItem {

    private final int id;
    private final String newsImage;
    private final String heading;
    private final String fullMessage;
    private int dot1 = R.drawable.dot_indicator;
    private int dot2 = R.drawable.dot_indicator;
    private int dot3 = R.drawable.dot_indicator;
    private int dot4 = R.drawable.dot_indicator;
    private int dot5 = R.drawable.dot_indicator;

    public NewsItem (int id, String newsImage, String heading, String fullMessage) {
        this.id = id;
        this.newsImage = newsImage;
        this.heading = heading;
        this.fullMessage = fullMessage;

        showIndicator();
    }

    public void showIndicator() {
        switch (id) {
            case 1:
                dot1 = R.drawable.dot_indicator_filled;
                break;
            case 2:
                dot2 = R.drawable.dot_indicator_filled;
                break;
            case 3:
                dot3 = R.drawable.dot_indicator_filled;
                break;
            case 4:
                dot4 = R.drawable.dot_indicator_filled;
                break;
            case 5:
                dot5 = R.drawable.dot_indicator_filled;
                break;
        }
    }

    public int getId() {
        return id;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public String getHeading() {
        return heading;
    }

    public String getFullMessage() {
        return fullMessage;
    }

    public int getDot1() {
        return dot1;
    }

    public int getDot2() {
        return dot2;
    }

    public int getDot3() {
        return dot3;
    }

    public int getDot4() {
        return dot4;
    }

    public int getDot5() {
        return dot5;
    }


}
