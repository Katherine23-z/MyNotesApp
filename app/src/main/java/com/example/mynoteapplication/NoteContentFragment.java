package com.example.mynoteapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;


public class NoteContentFragment extends Fragment {
    private Note note;
    private Publisher publisher;

    public static final String ARG_PARAM1 = "Param_Note";

    private TextInputEditText title;
    private TextInputEditText content;
    private DatePicker datePicker;

    public NoteContentFragment() {

    }

    public static NoteContentFragment newInstance(Note note) {
        NoteContentFragment fragment = new NoteContentFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, note);
        fragment.setArguments(args);
        return fragment;
    }
    
    public static NoteContentFragment newInstance(){
        NoteContentFragment fragment = new NoteContentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity)context;
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        publisher = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_content, container, false);
        initView(view);

        if(note != null){
            populateView();
        }
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        note = collectNoteContent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        publisher.notifySingle(note);
    }

    private void initView(View view) {
        title = view.findViewById(R.id.inputTitle);
        content = view.findViewById(R.id.inputContent);
        datePicker = view.findViewById(R.id.inputDate);
    }

    private void populateView(){
        title.setText(note.getTitle());
        content.setText(note.getText());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(note.getDate());
        this.datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);
    }

    private Note collectNoteContent(){
        String title = this.title.getText().toString();
        String content = this.content.getText().toString();
        Date date = getDateFromDatePicker();
        int picture;
        if (note != null){
            picture = note.getPicture();
        } else {
            picture = R.drawable.typewriter;
        }
        return new Note(title, content, picture, date);
    }

    private Date getDateFromDatePicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.datePicker.getYear());
        calendar.set(Calendar.MONTH, this.datePicker.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, this.datePicker.getDayOfMonth());
        return calendar.getTime();
    }
}