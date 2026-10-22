package com.example.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = findViewById(R.id.sendRequest);
        Button openResponse = findViewById(R.id.openResponse);

        send.setOnClickListener(view -> {
            Intent request = new Intent("com.example.sample.REQUEST");
            request.setClass(this, RequestReceiver.class);
            request.putExtra("message", "Hello from MainActivity");
            sendBroadcast(request);
            Toast.makeText(this, "Request sent", Toast.LENGTH_SHORT).show();
        });

        openResponse.setOnClickListener(view ->
                startActivity(new Intent(this, ResponderActivity.class)));
    }
}
