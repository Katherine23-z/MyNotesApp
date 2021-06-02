package com.example.mynoteapplication;

import java.util.GregorianCalendar;

public class Note {
    String title;
    String date;
    String text;

    public Note(String title, String date, String description) {
        this.title = title;
        this.date = date;
        this.text = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.text = description;
    }
}
