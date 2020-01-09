package com.frangrgec.numbershapesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputNumber;

        public void makeToast(String text)
        {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            
        }

        //Checks if the number is a Square
        public boolean isSquare(int number)
        {
            if(number<0)
                return false;

            if(Math.sqrt(number)==(int)Math.sqrt(number))
                return true;
            else
                return false;
        }


        //Checks if the number is Triangular
        public boolean isTriangular(int number)
        {
            if(isSquare(8*number+1))
                return true;
            else
                return false;
        }



        //Checks the number what shape it is
        public void numberCheckButton(View view)
        {

            if(inputNumber.getText().toString().isEmpty())
            {
                makeToast("Please enter a number!");
            }
            else
            {
                int number=Integer.parseInt(inputNumber.getText().toString());

                if(isTriangular(number)&&isSquare(number))
                {
                    makeToast("The number is Triangular and Square!");
                }
                else if(isTriangular(number)&&!isSquare(number))
                {
                    makeToast("The number is Triangular!");
                }
                else if(!isTriangular(number)&&isSquare(number))
                {
                    makeToast("The number is Square!");
                }
                else
                {
                    makeToast("The number is nor Triangular nor Square!");
                }
            }

        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber= findViewById(R.id.inputEditText);
    }
}
