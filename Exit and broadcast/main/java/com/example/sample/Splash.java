package com.example.sample;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    Handler handler;
    TextView textView;
    MediaPlayer mediaPlayer;


    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash2);


        if (getSupportActionBar()!=null)
            getSupportActionBar().hide();
        mediaPlayer=MediaPlayer.create(this,R.raw.audio1);





    }
}