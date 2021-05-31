package com.example.mynoteapplication;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class NoteContentFragment extends Fragment {

    public static final String ARG_PARAM1 = "index";
    public static final int DEFAULT_INDEX = 0;
    private int index = DEFAULT_INDEX;

    public NoteContentFragment() {

    }

    public static NoteContentFragment newInstance(int index) {
        NoteContentFragment fragment = new NoteContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_PARAM1, DEFAULT_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_content, container, false);
        TextView content = view.findViewById(R.id.note_content);
        TypedArray notes = getResources().obtainTypedArray(R.array.note_content);
        content.setText(notes.getResourceId(index, DEFAULT_INDEX));
        content.setTextSize(30);
        ImageView image = view.findViewById(R.id.image);
        TypedArray images = getResources().obtainTypedArray(R.array.images);
        image.setImageResource(images.getResourceId(index, DEFAULT_INDEX));
        return view;
    }



}