package com.example.android.quakereport;

import java.text.SimpleDateFormat;

/**
 * Created by Pavlo Kotelnytskyi on 8/29/2017.
 */

public class Earthquake {
    private double mag;
    private String place;
    private long date;
    private String url;

    public Earthquake(double mag, String place, long date, String url) {
        this.mag = mag;
        this.place = place;
        this.date = date;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormat.format(date);
    }

    public String getTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(date);
    }
}
