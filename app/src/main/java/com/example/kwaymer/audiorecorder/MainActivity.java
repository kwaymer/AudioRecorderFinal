package com.example.kwaymer.audiorecorder;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button files;
    private Button credits;
    private Button beats;
    private Button play, stop, record;
    private MediaRecorder myAudioRecorder;
    private String outputFile;

    public void OnClickButtonListener(){
        files = (Button)findViewById(R.id.files);
        files.setOnClickListener(
                new View.OnClickListener()  {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.kwaymer.audiorecorder.adapter.ListViewAdapter");
                        startActivity(intent);
                    }
        });

        beats = (Button)findViewById(R.id.beats);
        beats.setOnClickListener(
                new View.OnClickListener()  {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.kwaymer.audiorecorder.AudioPlayer");
                        startActivity(intent);
                    }

        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickButtonListener();
        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        record = (Button) findViewById(R.id.record);
        play.setEnabled(false);
        stop.setEnabled(false);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";


        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IllegalStateException ise) {
                } catch (IOException ioe) {
                }
                record.setEnabled(false);
                stop.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Recording", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                record.setEnabled(true);
                stop.setEnabled(false);
                play.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Stopping", Toast.LENGTH_LONG).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();

                try {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    Toast.makeText(getApplicationContext(), "Playing", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                }
            }

        });

    }

    }
