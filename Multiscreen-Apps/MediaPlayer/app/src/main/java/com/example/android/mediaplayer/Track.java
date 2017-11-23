package com.example.android.mediaplayer;

/**
 * Created by Pavlo Kotelnytskyi on 8/22/2017.
 */

public class Track {
    private int resID;
    private String author;
    private String name;

    public Track(String author, String name, int resID) {
        this.author = author;
        this.name = name;
        this.resID = resID;
    }

    public int getResID() {
        return resID;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
