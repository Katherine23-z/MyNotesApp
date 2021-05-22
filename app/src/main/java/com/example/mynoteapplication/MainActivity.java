package com.example.mynoteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }*/
        /*if(savedInstanceState == null){
            NotesFragment notes = new NotesFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.note_container, notes)
                    .commit();
        }*/
    }
}