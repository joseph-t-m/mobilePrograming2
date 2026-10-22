package com.example.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class ResponderActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String answer = getSharedPreferences("broadcast_lab", Context.MODE_PRIVATE)
                .getString("last_response", "No request received yet");
        TextView result = findViewById(R.id.responseText);
        result.setText(answer);
    }
}
