package com.frangrgec.braingamedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    //TODO Write a permanent highscore board
    //TODO Write a difficulty setting

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button startButton;
    Button difficultyButton;
    Button highScoreButton;
    Button tryAgain;
    Button mainMenu;
    TextView timerText;
    TextView scoreText;
    TextView resultText;
    TextView questionText;
    RecyclerView highScoreList;
    CountDownTimer gameTime;
    int firstRand;
    int secondRand;
    int result;
    int roundResult=0;
    int roundCount=1;

    //Animates the buttons and hides/shows them
    public void buttonPrepare(Button button,int state)
    {
        if(state==1)
        {
            button.animate().alpha(0f).setDuration(500);
            button.setEnabled(false);
        }
        else
        {
            button.animate().alpha(1f).setDuration(1000);
            button.setEnabled(true);
        }

    }

    //Animates the text and shows them
    public void textPrepare(TextView text)
    {
        text.animate().alpha(1f).setDuration(1000);
    }

    //Sets start round
    //TODO Start countdown
    public void prepareStartRound()
    {
        buttonPrepare(startButton,1);
        buttonPrepare(highScoreButton,1);
        buttonPrepare(difficultyButton,1);

        buttonPrepare(button1,0);
        buttonPrepare(button2,0);
        buttonPrepare(button3,0);
        buttonPrepare(button4,0);

        textPrepare(timerText);
        textPrepare(scoreText);
        textPrepare(questionText);

    }

    //Starts the game timer
    public void timerStart()
    {
        gameTime=new CountDownTimer(30100,1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                int seconds=(int)millisUntilFinished/1000;
                String secondString=Integer.toString(seconds);

                if (seconds <= 9)
                {
                    secondString= "0"+ seconds;
                }

                timerText.setText(secondString);
            }

            @Override
            public void onFinish()
            {
                resultText.setText("Your score was: " + roundResult + "/" + (roundCount-1) + "!");
                resultText.animate().alpha(1f).setDuration(1000);

                tryAgain.animate().alpha(1f).setDuration(1000);
                tryAgain.setEnabled(true);

                mainMenu.animate().alpha(1f).setDuration(1000);
                mainMenu.setEnabled(true);

                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);

            }

        }.start();
    }


    //Generates a random values for the question
    public void questionGenerate()
    {
        firstRand = new Random().nextInt(50);
        secondRand = new Random().nextInt(50);

        questionText.setText(firstRand + " + " + secondRand + " =?");

        answerButtonSet();
    }

    //Checks if the result and the button text are different
    public String checkButton(int result)
    {
        int fakeResult=new Random().nextInt(100);

        if(fakeResult==result)
            checkButton(result);
        return String.valueOf(fakeResult);
    }

    //Generates the button results
    public void answerButtonSet()
    {
        result=firstRand+secondRand;
        int button= new Random().nextInt(4);

        switch(button)
        {
            case 0:
                button1.setText(String.valueOf(result));
                button2.setText(checkButton(result));
                button3.setText(checkButton(result));
                button4.setText(checkButton(result));
                break;
            case 1:
                button1.setText(checkButton(result));
                button2.setText(String.valueOf(result));
                button3.setText(checkButton(result));
                button4.setText(checkButton(result));
                break;
            case 2:
                button1.setText(checkButton(result));
                button2.setText(checkButton(result));
                button3.setText(String.valueOf(result));
                button4.setText(checkButton(result));
                break;
            case 3:
                button1.setText(checkButton(result));
                button2.setText(checkButton(result));
                button3.setText(checkButton(result));
                button4.setText(String.valueOf(result));
                break;
        }

    }

    //Checks user guess and start new round
    public void answerButton(View button)
    {
        Button userButton=findViewById(button.getId());
        String userGuessString=userButton.getText().toString();
        int userGuessNumber=Integer.parseInt(userGuessString);


        if(userGuessNumber==result)
        {
                roundResult+=1;
                roundCount+=1;
                scoreText.setText(roundResult + "/" + (roundCount-1));
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                questionGenerate();
        }
        else
        {
                roundCount+=1;
                scoreText.setText(roundResult + "/" + (roundCount-1));
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
                questionGenerate();
        }
    }

    //Resets to default values
    public void resetToDefault()
    {
        roundResult=0;
        roundCount=1;
        tryAgain.animate().alpha(0f).setDuration(500);
        tryAgain.setEnabled(false);
        mainMenu.animate().alpha(0f).setDuration(500);
        mainMenu.setEnabled(false);
        resultText.animate().alpha(0f).setDuration(500);
        scoreText.setText(roundResult + "/0");
    }

    //Restarts the game and starts a new
    public void tryAgain(View view)
    {
        buttonPrepare(button1,0);
        buttonPrepare(button2,0);
        buttonPrepare(button3,0);
        buttonPrepare(button4,0);

        resetToDefault();
        startGame(view);
    }

    //Opens the main menu and hides the rest
    public void mainMenu(View view)
    {
        scoreText.animate().alpha(0f).setDuration(500);
        timerText.animate().alpha(0f).setDuration(500);
        questionText.animate().alpha(0f).setDuration(500);
        resetToDefault();

        button1.animate().alpha(0f).setDuration(500);
        button2.animate().alpha(0f).setDuration(500);
        button3.animate().alpha(0f).setDuration(500);
        button4.animate().alpha(0f).setDuration(500);

        buttonPrepare(startButton,0);
        buttonPrepare(highScoreButton,0);
        buttonPrepare(difficultyButton,0);

    }

    //Starts the game
    public void startGame(View view)
    {
        prepareStartRound();
        timerStart();
        questionGenerate();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton =findViewById(R.id.startButton);
        difficultyButton =findViewById(R.id.difficultyButton);
        highScoreButton =findViewById(R.id.highScoreButton);
        tryAgain =findViewById(R.id.tryAgainButton);
        mainMenu =findViewById(R.id.menuButton);

        button1 =findViewById(R.id.answerButton1);
        button2 =findViewById(R.id.answerButton2);
        button3 =findViewById(R.id.answerButton3);
        button4 =findViewById(R.id.answerButton4);

        timerText =findViewById(R.id.timeTextView);
        scoreText =findViewById(R.id.scoreTextView);
        questionText =findViewById(R.id.questionTextView);
        resultText =findViewById(R.id.resultTextView);
        highScoreList =findViewById(R.id.highScoreList);


    }
}
