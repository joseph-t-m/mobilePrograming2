package com.example.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class RequestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        String response = "Received: " + message + "\nAnswer: OK";


        Toast.makeText(context, "Answer: OK", Toast.LENGTH_SHORT).show();

        context.getSharedPreferences("broadcast_lab", Context.MODE_PRIVATE)
                .edit().putString("last_response", response).apply();

        MediaPlayer player = MediaPlayer.create(context, R.raw.audio1);
        if (player != null) {
            player.setOnCompletionListener(MediaPlayer::release);
            player.start();
        }
    }
}
