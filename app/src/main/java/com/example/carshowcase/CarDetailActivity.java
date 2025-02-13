package com.example.carshowcase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CarDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show back button
            getSupportActionBar().setTitle("Car Details");
        }

        // Get UI elements
        ImageView carImage = findViewById(R.id.detailCarImage);
        TextView carName = findViewById(R.id.detailCarName);
        TextView carDescription = findViewById(R.id.detailCarDescription);
        Button goBackButton = findViewById(R.id.goBackButton); // Define the button

        // Get data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("car_name");
        int image = intent.getIntExtra("car_image", 0);
        String description = intent.getStringExtra("car_description");

        // Set data to views
        carName.setText(name);
        carImage.setImageResource(image);
        carDescription.setText(description);

        // Handle back button click
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close activity and go back
            }
        });
    }

    // Load menu in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // Handle menu clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) { // Toolbar back button
            finish();
            return true;
        } else if (id == R.id.action_home) { // Home button
            Intent homeIntent = new Intent(this, MainActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear backstack
            startActivity(homeIntent);
            return true;
        } else if (id == R.id.action_about) { // About button
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
