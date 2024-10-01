package ssi.master.library;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import ssi.master.library.services.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize the VideoView
        VideoView videoView = findViewById(R.id.videoView);

        // Set the path to the video file in res/raw
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_video);
        videoView.setVideoURI(videoUri);

        // Start the video
        videoView.start();

        // Listen for when the video completes
        videoView.setOnCompletionListener(mp -> {
            // Once the video is finished, transition to LoginActivity
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish(); // End SplashActivity so it's removed from the back stack
        });
    }
}
