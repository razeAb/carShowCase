package com.example.carshowcase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CarDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

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
}
