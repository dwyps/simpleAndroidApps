package com.frangrgec.timersdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int isItClicked;
    TextView countdown;
    SeekBar time;
    MediaPlayer alarm;
    CountDownTimer timer;

    //Updates the timer values
    public void timerUpdate(int secondsLeft)
    {
        int minutes = secondsLeft/60;
        int seconds = secondsLeft-minutes*60;

        String secondString=Integer.toString(seconds);

        if (seconds <= 9) {
            secondString= "0"+ seconds;
        }
        countdown.setText(minutes + ":" + secondString);
    }

    //Stops and starts the timer
    public void toggleWatch(View view)
    {
        switch (isItClicked) {

            case 0:

                time.setEnabled(false);
                timer= new CountDownTimer(time.getProgress()*1000 + 100, 1000)
                {

                    @Override
                    public void onTick(long millisUntilFinished)
                    {
                        timerUpdate((int) millisUntilFinished / 1000);
                    }

                    @Override
                    public void onFinish()
                    {

                        countdown.setText("0:00");

                        alarm.seekTo(0);
                        alarm.start();


                        new CountDownTimer(5000,1000)
                        {

                            @Override
                            public void onTick(long millisUntilFinished)
                            {

                            }

                            @Override
                            public void onFinish() {

                                alarm.pause();
                                timer.cancel();
                                time.setProgress(300);
                                timerUpdate(time.getProgress());
                                time.setEnabled(true);
                                isItClicked = 0;

                            }
                        }.start();
                    }
                }.start();

                isItClicked=1;
                break;

            case 1:

                timer.cancel();
                time.setProgress(300);
                timerUpdate(time.getProgress());
                time.setEnabled(true);
                isItClicked=0;

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdown=findViewById(R.id.countdownView);
        time=findViewById(R.id.timeBar);
        alarm = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
        isItClicked=0;
        time.setMax(600);
        time.setProgress(300);

        //Sets the timer time
        time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                timerUpdate(progress);
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
