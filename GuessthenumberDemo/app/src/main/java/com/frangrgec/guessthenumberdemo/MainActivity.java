package com.frangrgec.guessthenumberdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber;
    EditText userEditText;

    public void makeToast(String string)
    {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }


    //Checks the user guess and win condition
    public void checkNumber (View view)
    {

        int userNumber= Integer.parseInt(userEditText.getText().toString());

        if(userNumber>randomNumber)
        {
            makeToast("The number is lower!");
        }
        else if(userNumber<randomNumber)
        {
            makeToast("The number is higher!");
        }
        else
        {
            makeToast("You guessed the correct number: " + randomNumber + ". Try Again!");
            randomNumber= new Random().nextInt(21);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEditText= findViewById(R.id.inputEditText);

        randomNumber= new Random().nextInt(21);

    }
}
