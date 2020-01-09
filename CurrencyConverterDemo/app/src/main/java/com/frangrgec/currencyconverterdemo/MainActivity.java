package com.frangrgec.currencyconverterdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
//import android.widget.EditText;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText inputDollarAmount;
    TextView euroAmount;

    //Takes the user input and converts to euros
    public void convertButtonFunction(View view)
    {

        double dollarAmount= Double.parseDouble(inputDollarAmount.getText().toString());
        double convertEuro= dollarAmount*0.9;

        euroAmount.setText(String.format("%.2f",convertEuro) + " €");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputDollarAmount= findViewById(R.id.inputCurrencyTextEdit);
        euroAmount= findViewById(R.id.convertedTextView);

    }
}
