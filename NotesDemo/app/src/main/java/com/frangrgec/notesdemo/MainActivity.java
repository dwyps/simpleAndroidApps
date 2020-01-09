package com.frangrgec.notesdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> notes;
    static ArrayAdapter<String> adapter;
    ListView noteList;

    //Adds a menu for adding new notes
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater= getMenuInflater();

        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //New note button functionality
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.addNote)
        {
            Intent intent= new Intent(getApplicationContext(),NoteActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes=new ArrayList<>();
        noteList=findViewById(R.id.noteList);

        SharedPreferences sharedPreferences= this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("note", null);

        //Adds the default note if there aren't any
        if(set==null)
        {
            notes.add("Add your note...");

        } else {

            notes= new ArrayList<>(set);

        }


        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        noteList.setAdapter(adapter);

        //Opens the note for editing
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent=new Intent(getApplicationContext(),NoteActivity.class);
                intent.putExtra("noteId",position);
                startActivity(intent);
            }

        });

        //Gives you the option to delete the note
        noteList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setNegativeButton("No",null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                notes.remove(position);
                                adapter.notifyDataSetChanged();
                                SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                                HashSet<String> set=new HashSet<>(MainActivity.notes);
                                sharedPreferences.edit().putStringSet("note", set).apply();

                            }
                        })
                        .show();

                return true;
            }
        });
    }
}
