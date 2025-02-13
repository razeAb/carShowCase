package com.example.carshowcase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button; // Import Button

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Find the button by its ID
        Button goBackButton = findViewById(R.id.goBackButton);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close activity and go back
            }
        });
    }
}
