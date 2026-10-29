package com.example.sample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import android.app.AlertDialog;

public class MainActivity extends Activity {

    TextView networkText, statusText;
    Button sendButton, responseButton;

    RequestReceiver receiver;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnExit = findViewById(R.id.btnExit);

        btnExit.setOnClickListener(view -> {

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Test");
            builder.setMessage("Do you eant to Exit?");

            builder.setPositiveButton("Yes i want! 🖐 ", (dialog, which) -> {
                finish();
            });

            builder.setNegativeButton("No", (dialog, which) -> {
                dialog.cancel();
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        });

        networkText = findViewById(R.id.networkText);
        statusText = findViewById(R.id.statusText);

        sendButton = findViewById(R.id.sendButton);
        responseButton = findViewById(R.id.responseButton);
        ////////////////////////audio//////////////////////////////
        mediaPlayer = MediaPlayer.create(this, R.raw.audio1);

        receiver = new RequestReceiver(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.sample.REQUEST");
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(
                    receiver,
                    filter,
                    Context.RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(receiver, filter);
        }

        updateNetworkText();

        sendButton.setOnClickListener(view -> {

            Intent intent = new Intent(
                    "com.example.sample.REQUEST");

            intent.setPackage(getPackageName());

            intent.putExtra("message", "Hello");

            sendBroadcast(intent);
        });

        responseButton.setOnClickListener(view -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    ResponderActivity.class);

            startActivity(intent);
        });
    }

    // Play audio1
    public void playAudio() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }

            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
    }

    // Change text based on network type
    public void updateNetworkText() {

        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(
                        CONNECTIVITY_SERVICE);

        NetworkCapabilities capabilities =
                manager.getNetworkCapabilities(
                        manager.getActiveNetwork());

        if (capabilities == null) {
            networkText.setText("No Internet Connection");
            networkText.setTextColor(Color.RED);
        } else if (capabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI)) {
            networkText.setText("Connected to WiFi");
            networkText.setTextColor(Color.BLUE);

        } else if (capabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR)) {
            networkText.setText("Connected to Mobile Data");
            networkText.setTextColor(Color.BLUE);
        } else {
            networkText.setText("Other Network");
            networkText.setTextColor(Color.BLACK);

        }
    }

    public void showResponse(String message) {
        statusText.setText(message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateNetworkText();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        super.onDestroy();
    }
}

//        ---------------------------------------------------
//Button send = findViewById(R.id.sendRequest);
//Button openResponse = findViewById(R.id.openResponse);
//
//        send.setOnClickListener(view -> {
//Intent request = new Intent("com.example.sample.REQUEST");
//            request.setClass(this, RequestReceiver.class);
//            request.putExtra("message", "Hello from MainActivity");
//sendBroadcast(request);
//            Toast.makeText(this, "Request sent", Toast.LENGTH_SHORT).show();
//        });
//
//                openResponse.setOnClickListener(view ->
//startActivity(new Intent(this, ResponderActivity.class)));
//    }
//}
