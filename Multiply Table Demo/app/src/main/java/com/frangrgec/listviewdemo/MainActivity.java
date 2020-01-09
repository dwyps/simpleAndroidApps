package com.frangrgec.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    ListView firstList;
    SeekBar multiSeekBar;
    ArrayList<Integer> numbers;
    ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstList= findViewById(R.id.firstListView);
        multiSeekBar=findViewById(R.id.multiSeekBar);

        numbers=new ArrayList<>(asList(1,2,3,4,5,6,7,8,9));

        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, numbers);
        firstList.setAdapter(adapter);

        multiSeekBar.setMin(1);
        multiSeekBar.setMax(10);
        multiSeekBar.setProgress(1);


        multiSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //Changes the values in the list view according to the seek bar value
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                Integer[] numbersMulti={1,2,3,4,5,6,7,8,9};

                for (int i=0;i<numbers.size();i++)
                {
                    Toast.makeText(MainActivity.this, "Multiply by " + progress, Toast.LENGTH_SHORT).show();
                    numbersMulti[i]*=progress;
                    numbers.set(i, numbersMulti[i]);
                    firstList.setAdapter(adapter);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
