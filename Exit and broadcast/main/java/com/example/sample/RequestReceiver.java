
package com.example.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class RequestReceiver extends BroadcastReceiver {

    MainActivity activity;

    public RequestReceiver(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        // Receive request
        if ("com.example.sample.REQUEST".equals(action)) {

            String message = intent.getStringExtra("message");

            context.getSharedPreferences(
                            "broadcast_lab",
                            Context.MODE_PRIVATE)
                    .edit()
                    .putString("reply",
                            "Received: " + message +
                                    "\nAnswer: OK")
                    .apply();

            Toast.makeText(context,
                    "Request Received",
                    Toast.LENGTH_SHORT).show();

            activity.showResponse("Answer: OK");
            activity.playAudio();
        }

        if (Intent.ACTION_SCREEN_OFF.equals(action)) {

            activity.playAudio();
        }

        if (Intent.ACTION_SCREEN_ON.equals(action)) {

            activity.playAudio();
        }

        if (ConnectivityManager.CONNECTIVITY_ACTION
                .equals(action)) {

            activity.updateNetworkText();
        }
    }
}
