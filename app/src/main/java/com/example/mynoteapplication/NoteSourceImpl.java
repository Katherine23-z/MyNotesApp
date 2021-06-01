package com.example.mynoteapplication;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NoteSourceImpl implements NoteSource{
    private List<Note> list;
    private Resources res;

    public NoteSourceImpl(Resources res) {
        list = new ArrayList<>(5);
        this.res = res;
    }

    public NoteSourceImpl init(){
        String[] titles = res.getStringArray(R.array.Titles);
        String[] content = res.getStringArray(R.array.note_content);
        int[] images = getImagesArray();

        for(int i = 0; i < content.length; i++){
            list.add(new Note(
                    titles[i],
                    content[i],
                    images[i],
                    Calendar.getInstance().getTime()
            ));
        }return this;
    }

    private int[] getImagesArray() {
        TypedArray array = res.obtainTypedArray(R.array.images);
        int[] images = new int[array.length()];
        for (int i = 0; i < array.length(); i++) {
            images[i] = array.getResourceId(i, 0);
        }return images;
    }

    @Override
    public Note getNote(int position) {
        return list.get(position);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void deleteNote(int position) {
        list.remove(position);
    }

    @Override
    public void updateNote(int position, Note note) {
        list.set(position, note);
    }

    @Override
    public void addNote(Note note) {
        list.add(note);
    }

    @Override
    public void clearNote() {
        list.clear();
    }
}
