package com.example.mynoteapplication;

public interface NoteSource {
    Note getNote(int position);
    int size();
    void deleteNote(int position);
    void updateNote(int position, Note note);
    void addNote(Note note);
    void clearNote();
}
