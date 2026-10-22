package com.example.sample;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;

public class AudioActivity extends Activity {

    MediaPlayer mediaPlayer;
    Button play, pause, stop;
    int length = 0;
    SeekBar seekBar;
    Handler handler;
    UpdateSeekBar updateSeekBar = new UpdateSeekBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        seekBar = findViewById(R.id.seekBar);

        prepareMusic();
        handler = new Handler();

        play.setOnClickListener(view -> {
            mediaPlayer.seekTo(length);
            mediaPlayer.start();
        });

        pause.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                length = mediaPlayer.getCurrentPosition();
            }
        });

        stop.setOnClickListener(view -> {
            mediaPlayer.stop();
            length = 0;
            prepareMusic();
        });

        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(
                            SeekBar bar, int progress,
                            boolean fromUser) {

                        if (fromUser) {
                            mediaPlayer.seekTo(progress);
                            length = progress;
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar bar) { }

                    @Override
                    public void onStopTrackingTouch(SeekBar bar) { }
                });

        handler.post(updateSeekBar);
    }

    class UpdateSeekBar implements Runnable {
        @Override
        public void run() {
            seekBar.setProgress(
                    mediaPlayer.getCurrentPosition());

            handler.postDelayed(this, 100);
        }
    }

    private void prepareMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(
                getApplicationContext(),
                R.raw.audio1);

        if (seekBar != null) {
            seekBar.setMax(mediaPlayer.getDuration());
            seekBar.setProgress(0);
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(updateSeekBar);
        mediaPlayer.release();
        super.onDestroy();
    }
}