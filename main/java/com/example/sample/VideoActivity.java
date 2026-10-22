package com.example.sample;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import com.example.sample.R;

public class VideoActivity extends Activity {

    VideoView videoView;
    Button playVideo, pauseVideo, stopVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);
        playVideo = findViewById(R.id.playVideo);
        pauseVideo = findViewById(R.id.pauseVideo);
        stopVideo = findViewById(R.id.stopVideo);

        Uri uri = Uri.parse(
                "android.resource://" +
                        getPackageName() + "/" + R.raw.video1
        );

        videoView.setVideoURI(uri);

        playVideo.setOnClickListener(view -> {
            videoView.start();
        });

        pauseVideo.setOnClickListener(view -> {
            videoView.pause();
        });

        stopVideo.setOnClickListener(view -> {
            videoView.stopPlayback();
            videoView.setVideoURI(uri);
        });
    }
}