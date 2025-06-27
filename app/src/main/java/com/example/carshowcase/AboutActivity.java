package com.example.carshowcase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    RatingBar ratingbar;
    Button contactButton, shareButton, goBackButton;
    SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "CarShowPrefs";
    private static final String RATING_KEY = "user_rating";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Connect UI
        ratingbar = findViewById(R.id.ratingBar);
        contactButton = findViewById(R.id.contactButton);
        shareButton = findViewById(R.id.shareButton);
        goBackButton = findViewById(R.id.goBackButton);

        // Set up SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Load saved rating
        float savedRating = sharedPreferences.getFloat(RATING_KEY, 0f);
        ratingbar.setRating(savedRating);

        // Save rating when changed
        ratingbar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat(RATING_KEY, rating);
            editor.apply();
            Toast.makeText(this, "Rating saved: " + rating, Toast.LENGTH_SHORT).show();
        });

        // Contact Us button
        contactButton.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:razea1@hotmail.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback about Car Showcase App");
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
        });

        // Share App button
        shareButton.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out the Car Showcase App!");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        // Go Back button
        goBackButton.setOnClickListener(v -> finish());
    }
}
