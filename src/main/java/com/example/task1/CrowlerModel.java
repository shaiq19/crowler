package com.example.task1;

import java.util.Date;

public class CrowlerModel {
    private String companyName;
    private String newsSource;
    private String dateTime;

    public CrowlerModel(String companyName, String newsSource, String dateTime) {
        this.companyName = companyName;
        this.newsSource = newsSource;
        this.dateTime = dateTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "CrowlerModel{" +
                "companyName='" + getCompanyName() + '\'' +
                ", newsSource='" + getNewsSource() + '\'' +
                ", dateTime='" + getDateTime() + '\'' +
                '}';
    }
}

