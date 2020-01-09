package com.frangrgec.notesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class NoteActivity extends AppCompatActivity {

    EditText noteContent;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteContent=findViewById(R.id.noteText);
        Intent intent=getIntent();
        noteId=intent.getIntExtra("noteId",-1);

        //Sets the context of the note
        if(noteId!=-1)
        {
            noteContent.setText(MainActivity.notes.get(noteId));

        }else{

            MainActivity.notes.add("");
            noteId=MainActivity.notes.size()-1;
            MainActivity.adapter.notifyDataSetChanged();

        }


        noteContent.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //Saves the context of the note permanently
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.length()!=0)
                {
                        SharedPreferences sharedPreferences= getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                        HashSet<String> set=new HashSet<>(MainActivity.notes);

                        sharedPreferences.edit().putStringSet("note", set).apply();

                        MainActivity.notes.set(noteId,noteContent.getText().toString());
                        MainActivity.adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
