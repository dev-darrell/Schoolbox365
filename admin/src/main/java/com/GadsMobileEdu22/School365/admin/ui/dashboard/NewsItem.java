package com.GadsMobileEdu22.School365.admin.ui.dashboard;

import java.util.UUID;

public class NewsItem {

    private int id = UUID.randomUUID().hashCode();
    private int newsImage;
    private String heading;
    private String fullMessage;

    public NewsItem (int newsImage, String heading, String fullMessage) {
        this.newsImage = newsImage;
        this.heading = heading;
        this.fullMessage = fullMessage;
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
}
