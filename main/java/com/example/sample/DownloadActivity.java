package com.example.sample;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DownloadActivity extends Activity {

    EditText urlInput;
    Button download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlInput = findViewById(R.id.urlInput);
        download = findViewById(R.id.download);

        download.setOnClickListener(view -> {

            String url = urlInput.getText()
                    .toString().trim();

            Uri uri = Uri.parse(url);

            String scheme = uri.getScheme();

            if (uri.getHost() == null ||
                    (!"https".equalsIgnoreCase(scheme)
                            && !"http".equalsIgnoreCase(scheme))) {

                urlInput.setError("Enter valid URL");
                return;
            }

            DownloadManager manager =
                    (DownloadManager) getSystemService(
                            Context.DOWNLOAD_SERVICE);

            DownloadManager.Request request =
                    new DownloadManager.Request(uri);

            request.setTitle("Downloading file");

            request.setNotificationVisibility(
                    DownloadManager.Request
                            .VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            );

            if (Build.VERSION.SDK_INT >= 29) {
                String filename = URLUtil.guessFileName(
                        url, null, null);

                request.setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS,
                        filename);
            }

            try {
                manager.enqueue(request);

                Toast.makeText(this,
                        "Download queued",
                        Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(this,
                        "Download request failed",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}