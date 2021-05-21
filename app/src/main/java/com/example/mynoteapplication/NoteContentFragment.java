package com.example.mynoteapplication;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class NoteContentFragment extends Fragment {

    public static final String ARG_PARAM1 = "index";
    private static final int DEFAULT_INDEX = 0;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note_content, container, false);
        TextView textView = view.findViewById(R.id.note_content);
        TypedArray notes = getResources().obtainTypedArray(R.array.note_content);
        textView.setText(notes.getResourceId(index, DEFAULT_INDEX));
        textView.setTextSize(30);
        return view;
    }
}