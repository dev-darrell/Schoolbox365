package com.GadsMobileEdu22.School365.admin.ui.dashboard;

import com.GadsMobileEdu22.Schoolbox365.admin.R;

import java.util.UUID;

public class NewsItem {

    private int id;
    private int newsImage;
    private String heading;
    private String fullMessage;
    private int dot1 = R.drawable.dot_indicator;
    private int dot2 = R.drawable.dot_indicator;
    private int dot3 = R.drawable.dot_indicator;
    private int dot4 = R.drawable.dot_indicator;
    private int dot5 = R.drawable.dot_indicator;

    public NewsItem (int id, int newsImage, String heading, String fullMessage) {
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

    public int getNewsImage() {
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
