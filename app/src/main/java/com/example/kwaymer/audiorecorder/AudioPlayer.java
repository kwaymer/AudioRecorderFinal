package com.example.kwaymer.audiorecorder;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * Created by kwaymer on 4/12/17.
 */

public class AudioPlayer extends AppCompatActivity {

    private ImageButton GooglePlay;
    ObjectAnimator animation;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
        Button play = (Button) findViewById(R.id.button_play);
        Button pause = (Button) findViewById(R.id.button_pause);
        Button stop = (Button) findViewById(R.id.button_stop);
        GooglePlay = (ImageButton)findViewById(R.id.googleplay);

        //Animation setup
        animation = ObjectAnimator.ofFloat(GooglePlay, "rotation", 0f, 180f);
        animation.setDuration(2000);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());

        final MediaPlayer mp = MediaPlayer.create(AudioPlayer.this,R.raw.forest_green);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                startRotation();
            }

        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                stopRotation();
            }
        });
    }

    //start rotation for image
    private void startRotation() { animation.start();}
    //stop rotation for image
    private void stopRotation() { animation.end();}
}

