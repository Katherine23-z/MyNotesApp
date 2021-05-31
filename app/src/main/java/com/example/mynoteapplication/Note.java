package com.example.mynoteapplication;

import java.util.GregorianCalendar;

public class Note {
    String title;
    String text;
    int picture;

    public Note(String title, String description, int picture) {
        this.title = title;
        this.text = description;
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return text;
    }

    public int getPicture() {
        return picture;
    }
}
