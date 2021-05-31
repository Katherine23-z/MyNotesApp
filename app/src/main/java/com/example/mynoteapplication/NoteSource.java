package com.example.mynoteapplication;

public interface NoteSource {
    Note getNote(int position);
    int size();
}
