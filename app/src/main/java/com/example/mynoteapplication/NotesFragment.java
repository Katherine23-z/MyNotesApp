package com.example.mynoteapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NotesFragment extends Fragment {
    private boolean isLandscape;

    public NotesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        MyAdapter adapter = new MyAdapter(new NoteSourceImpl(getResources()).init());
        recyclerView.setAdapter(adapter);
        adapter.setListener(this::showContent);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(decoration);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            showContent(NoteContentFragment.DEFAULT_INDEX);
        }
    }

    void showContent(int index) {
        if (isLandscape) {
            showLandContent(index);
        } else {
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

    private void showPortContent(int index) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteContentActivity.class);
        intent.putExtra(NoteContentFragment.ARG_PARAM1, index);
        startActivity(intent);
    }
}