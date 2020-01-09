package com.frangrgec.basicphrasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    MediaPlayer audio;
    Button button;


    //Plays the phrase depending on the button pressed
    public void phraseButtons(View view)
    {
        button= (Button) view;

        switch (button.getId())
        {
            case R.id.button1:

                audio=MediaPlayer.create(this, R.raw.phrase1);
                if(audio.isPlaying())
                {
                    audio.stop();
                }
                audio.seekTo(0);
                audio.start();
                break;

            case R.id.button2:

                audio=MediaPlayer.create(this, R.raw.phrase2);
                if(audio.isPlaying())
                {
                    audio.stop();
                }
                audio.seekTo(0);
                audio.start();
                break;

            case R.id.button3:

                audio=MediaPlayer.create(this, R.raw.phrase3);
                if(audio.isPlaying())
                {
                    audio.stop();
                }
                audio.seekTo(0);
                audio.start();
                break;

            case R.id.button4:

                audio=MediaPlayer.create(this, R.raw.phrase4);
                if(audio.isPlaying())
                {
                    audio.stop();
                }
                audio.seekTo(0);
                audio.start();
                break;

            case R.id.button5:

                audio=MediaPlayer.create(this, R.raw.phrase5);
                if(audio.isPlaying())
                {
                    audio.stop();
                }
                audio.seekTo(0);
                audio.start();
                break;

            case R.id.button6:

                audio=MediaPlayer.create(this, R.raw.phrase6);
                if(audio.isPlaying())
                {
                    audio.stop();
                }
                audio.seekTo(0);
                audio.start();
                break;

            case R.id.button7:

                audio=MediaPlayer.create(this, R.raw.phrase7);
                if(audio.isPlaying())
                {
                    audio.stop();
                }
                audio.seekTo(0);
                audio.start();
                break;

            case R.id.button8:

                audio=MediaPlayer.create(this, R.raw.phrase8);
                if(audio.isPlaying())
                {
                    audio.stop();
                }
                audio.seekTo(0);
                audio.start();
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
