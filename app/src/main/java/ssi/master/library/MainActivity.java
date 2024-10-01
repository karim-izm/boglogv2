package ssi.master.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ssi.master.library.services.AddBookActivity;
import ssi.master.library.services.ListBookActivity;
import ssi.master.library.services.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "UserPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve the username passed from the LoginActivity
        String username = getIntent().getStringExtra("username");

        TextView tvWelcome = findViewById(R.id.tv_welcome);
        tvWelcome.setText("Welcome, " + username);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Logout button functionality
        findViewById(R.id.btn_logout).setOnClickListener(v -> {
            // Clear the saved credentials from SharedPreferences when logging out
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            // Redirect to LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Add Book button functionality
        findViewById(R.id.btn_add_book).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        // List Books button functionality
        findViewById(R.id.btn_list_books).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListBookActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }
}
