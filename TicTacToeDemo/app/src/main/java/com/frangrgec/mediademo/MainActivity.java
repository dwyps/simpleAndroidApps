package com.frangrgec.mediademo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int playerID;
    int[] score={0,0};

    public void makeToast(String text, int importance)
    {
        switch (importance)
        {
            case 1:
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, text, Toast.LENGTH_LONG).show();
                break;
        }

    }

    //Changes the round text view
    public void playerNameTextView(String string)
    {
        TextView playerName= findViewById(R.id.playerName);
        playerName.setText(string);
    }

    //Sets the first player
    public void choosePlayerStart()
    {
        if(new Random().nextBoolean())
        {
            playerNameTextView("Player 1");
            makeToast("Player 1 starts!",2);
            playerID=1;
        }
        else
        {
            playerNameTextView("Player 2");
            makeToast("Player 2 starts!", 2);
            playerID=2;
        }
    }

    //Starts the game
    public void startButton(View view)
    {
        TableLayout gameLayout=findViewById(R.id.gameTableLayout);
        gameLayout.setVisibility(View.VISIBLE);

        Button startButton= findViewById(R.id.startButton);
        startButton.animate().alpha(0f).setDuration(500);
        startButton.setVisibility(View.INVISIBLE);

        choosePlayerStart();
    }

    //Switches player after each press
    public void playerButton(View view)
    {
        ImageButton[] buttons= new ImageButton[9];
        buttons[0] = findViewById(R.id.playerButton1);
        buttons[1] = findViewById(R.id.playerButton2);
        buttons[2] = findViewById(R.id.playerButton3);
        buttons[3] = findViewById(R.id.playerButton4);
        buttons[4] = findViewById(R.id.playerButton5);
        buttons[5] = findViewById(R.id.playerButton6);
        buttons[6] = findViewById(R.id.playerButton7);
        buttons[7] = findViewById(R.id.playerButton8);
        buttons[8] = findViewById(R.id.playerButton9);

        switch (playerID)
        {
            case 1:
                player1(view, buttons);
                break;
            case 2:
                player2(view, buttons);
                break;
        }
    }

    //Player 1 turn play
    public void player1(View view, ImageButton[] buttons)
    {
        if (checkWin(buttons))
        {
            if(wasFieldPlayed(view))
            {
                setPlayerField(view,R.drawable.x,1);

                if (checkWin(buttons)) {
                    playerNameTextView("Player 2");
                    playerID = 2;
                }

            }
            else {
                makeToast("That field is full!",1);
            }
        }

    }

    //Player 2 turn play
    public void player2(View view, ImageButton[] buttons)
    {
        if (checkWin(buttons))
        {
            if(wasFieldPlayed(view))
            {
                setPlayerField(view,R.drawable.toe,2);
                if(checkWin(buttons))
                {
                    playerNameTextView("Player 1");
                    playerID = 1;
                }
            }
            else
            {
                makeToast("That field is full!",1);
            }
        }
    }

    //Sets the field to a sign depending which player has pressed it
    public void setPlayerField(View view, int playerSign, int tag)
    {
        ImageView sign = findViewById(view.getId());

        sign.setImageResource(playerSign);
        sign.animate().alpha(1f).setDuration(1000);
        sign.setTag(tag);
    }

    //Checks if the field was played
    public boolean wasFieldPlayed(View view)
    {
        ImageView image = findViewById(view.getId());

        return (!image.getTag().toString().equals("1")&&!image.getTag().toString().equals("2"));
    }


    //Sets the player score for draw or who won
    public void playerScore(ImageButton[] buttons)
    {
        TextView scorePlayer1 = findViewById(R.id.scorePlayer1);
        TextView scorePlayer2 = findViewById(R.id.scorePlayer2);

        if(checksDraw(buttons))
            {
                scorePlayer1.setText(String.valueOf(score[0] += 1));
                scorePlayer2.setText(String.valueOf(score[1] += 1));
                playerNameTextView("It's a Draw");
            }
        else
            {
                if(playerID==1) {
                    score[0] += 1;
                    scorePlayer1.setText(String.valueOf(score[0]));
                    playerNameTextView("Player " + playerID + " has Won!");
                }
                else{
                    score[1]+=1;
                    scorePlayer2.setText(String.valueOf(score[1]));
                    playerNameTextView("Player " + playerID + " has Won!");
                }
            }
    }

    //Reset button animation
    public void resetButtonAppearAnimation()
    {
        Button reset= findViewById(R.id.resetButton);
        reset.setVisibility(View.VISIBLE);
        reset.animate().alpha(1f).setDuration(500);
        makeToast("If you wanna play again press the reset button.",2);
    }

    //Resets all buttons to default and starts a new game
    public  void resetButton(View view)
    {
        ImageButton[] buttons=new ImageButton[9];
        buttons[0] = findViewById(R.id.playerButton1);
        buttons[1] = findViewById(R.id.playerButton2);
        buttons[2] = findViewById(R.id.playerButton3);
        buttons[3] = findViewById(R.id.playerButton4);
        buttons[4] = findViewById(R.id.playerButton5);
        buttons[5] = findViewById(R.id.playerButton6);
        buttons[6] = findViewById(R.id.playerButton7);
        buttons[7] = findViewById(R.id.playerButton8);
        buttons[8] = findViewById(R.id.playerButton9);

        Button reset= findViewById(R.id.resetButton);

        for(int i=0; i<9; i++)
        {
            buttons[i].setTag(i+3);
            buttons[i].animate().alpha(0f).setDuration(500);
            reset.animate().alpha(0f).setDuration(500);
            reset.setVisibility(View.INVISIBLE);
        }

        enableClickLayout(buttons,true);
        choosePlayerStart();
    }

    //Checks the fields for a draw
    //TODO simplfiy the check
    public boolean checksDraw(ImageButton[] buttons)
    {
            return (buttons[0].getTag().toString().equals("1")||buttons[0].getTag().toString().equals("2"))
                    &&(buttons[1].getTag().toString().equals("1")||buttons[1].getTag().toString().equals("2"))
                    &&(buttons[2].getTag().toString().equals("1")||buttons[2].getTag().toString().equals("2"))
                    &&(buttons[3].getTag().toString().equals("1")||buttons[3].getTag().toString().equals("2"))
                    &&(buttons[4].getTag().toString().equals("1")||buttons[4].getTag().toString().equals("2"))
                    &&(buttons[5].getTag().toString().equals("1")||buttons[5].getTag().toString().equals("2"))
                    &&(buttons[6].getTag().toString().equals("1")||buttons[6].getTag().toString().equals("2"))
                    &&(buttons[7].getTag().toString().equals("1")||buttons[7].getTag().toString().equals("2"))
                    &&(buttons[8].getTag().toString().equals("1")||buttons[8].getTag().toString().equals("2"));

    }

    //Finishes the game with a draw
    public void isItDraw(ImageButton[] buttons)
    {
        if(checksDraw(buttons))
        {
            resetButtonAppearAnimation();
            playerScore(buttons);
            enableClickLayout(buttons ,false);
            makeToast("If you wanna play again press the reset button.",2);
        }

    }

    //Checks win combinations
    //TODO simplify the check
    public boolean isItWon(ImageButton[] buttons)
    {
        return (buttons[0].getTag() == buttons[4].getTag() && buttons[4].getTag() == buttons[8].getTag())||
                (buttons[2].getTag() ==  buttons[4].getTag() && buttons[4].getTag() == buttons[6].getTag())||
                (buttons[0].getTag() == buttons[1].getTag() && buttons[1].getTag() == buttons[2].getTag())||
                (buttons[3].getTag() == buttons[4].getTag() && buttons[4].getTag() == buttons[5].getTag())||
                (buttons[6].getTag() == buttons[7].getTag() && buttons[7].getTag() == buttons[8].getTag())||
                (buttons[0].getTag() == buttons[3].getTag() && buttons[3].getTag() == buttons[6].getTag())||
                (buttons[1].getTag() == buttons[4].getTag() && buttons[4].getTag() == buttons[7].getTag())||
                (buttons[2].getTag() == buttons[5].getTag() && buttons[5].getTag() == buttons[8].getTag());
    }

    //Checks all game condition
    public boolean checkWin(ImageButton[] buttons)
    {
        if(isItWon(buttons))
        {
            endGame(buttons);
        }
        else isItDraw(buttons); return true;

    }

    //Enables or disables clicking on the fields
    public void enableClickLayout(ImageButton[] buttons, Boolean clickState)
    {
        for(int i=0;i<buttons.length;i++)
        {
            buttons[i].setEnabled(clickState);
        }
    }

    //Ends the game if it isn't a draw
    public void endGame(ImageButton[] buttons)
    {
        enableClickLayout(buttons ,false);
        playerScore(buttons);
        playerNameTextView("Player " + playerID + " has Won!");
        resetButtonAppearAnimation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

