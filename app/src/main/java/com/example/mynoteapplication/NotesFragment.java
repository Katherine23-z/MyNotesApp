package com.example.mynoteapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NotesFragment extends Fragment {
    private boolean isLandscape;

    List<Note> note;

    public NotesFragment() {
    }

    /*public static NotesFragment newInstance(String param1, String param2) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList (view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if(isLandscape){
            showContent(NoteContentFragment.DEFAULT_INDEX);
        }
    }

    private void initList(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        note = new ArrayList <Note>();
        note.add(new Note("Title1 ", "05.05.2021 ", "bla "));
        note.add(new Note("Title2 ", "11.07.21 ", "content "));
        note.add(new Note("Title3 ", "06.06.2021 ", "description "));
        for (int i = 0; i < note.size(); i++){
            Note position = note.get(i);
            TextView textView = new TextView(getContext());
            textView.setText(String.format("%s%s%s", position.getTitle(), position.getDate(), position.getDescription()));
            textView.setTextSize(30);
            linearLayout.addView(textView);
            final int fi = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showContent(fi);
                }
            });
        }
    }

    void showContent(int index){
        if (isLandscape){
            showLandContent(index);
        }else {
            showPortContent(index);
        }
    }

    private void showLandContent(int index) {
        NoteContentFragment contentFragment = NoteContentFragment.newInstance(index);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, contentFragment)
                .commit();
    }

    private void showPortContent(int index){
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteContentActivity.class);
        intent.putExtra(NoteContentFragment.ARG_PARAM1, index);
        startActivity(intent);
    }
}