package com.example.mynoteapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {
    private String title;
    private String text;
    private int picture;
    private Date date;


    public Note(String title, String description, int picture, Date date) {
        this.title = title;
        this.text = description;
        this.picture = picture;
        this.date = date;
    }

    protected Note(Parcel in) {
        title = in.readString();
        text = in.readString();
        picture = in.readInt();
        date = new Date(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(text);
        dest.writeInt(picture);
        dest.writeLong(date.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public int getPicture() {
        return picture;
    }

    public Date getDate() {
        return date;
    }

}
