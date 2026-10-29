
package com.example.sample;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class ResponderActivity extends Activity {

    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_responder);


        responseText = findViewById(R.id.responseText);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String response = getSharedPreferences(
                "broadcast_lab",
                Context.MODE_PRIVATE)
                .getString("reply", "No request received");

        responseText.setText(response);
        responseText.setTextColor(Color.GREEN);

    }



}
