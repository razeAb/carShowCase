package com.example.carshowcase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    String[] cars = {"BMW", "Ford", "Lamborghini", "Toyota"};
    int[] photos = {R.drawable.bmw, R.drawable.ford, R.drawable.lambo, R.drawable.toyota};
    ListView carsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ✅ Set up Toolbar before anything else
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ✅ Initialize ListView
        carsList = findViewById(R.id.myList);
        RaziAdapter raziAdapter = new RaziAdapter(this, cars, photos);
        carsList.setAdapter(raziAdapter);
    }

    // ✅ Inflate Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // ✅ Handle Toolbar Menu Clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_home) {
            Intent homeIntent = new Intent(this, MainActivity.class);
            startActivity(homeIntent);
            return true;
        } else if (id == R.id.action_about) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class RaziAdapter extends BaseAdapter {

        Context context;
        String[] carNames;
        int[] carImages;
        LayoutInflater inflater;

        RaziAdapter(Context context, String[] carNames, int[] carImages) {
            this.context = context;
            this.carNames = carNames;
            this.carImages = carImages;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return carNames.length;
        }

        @Override
        public Object getItem(int i) {
            return carNames[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = inflater.inflate(R.layout.list_item, viewGroup, false);
            }

            ImageView carPhoto = view.findViewById(R.id.carImage);
            TextView carName = view.findViewById(R.id.carName);

            carPhoto.setImageResource(carImages[i]);
            carName.setText(carNames[i]);

            // Car descriptions
            String[] descriptions = {
                    "BMW: A luxury car known for performance and elegance.",
                    "Ford: An American car brand with reliability and power.",
                    "Lamborghini: A high-performance sports car with a sleek design.",
                    "Toyota: A reliable and fuel-efficient car brand."
            };

            int position = i; // Use final variable for inner classes

            // ✅ Clicking the entire list item opens CarDetailActivity
            view.setOnClickListener(v -> {
                Intent intent = new Intent(context, CarDetailActivity.class);
                intent.putExtra("car_name", carNames[position]);
                intent.putExtra("car_image", carImages[position]); // Pass correct image ID
                intent.putExtra("car_description", descriptions[position]);
                context.startActivity(intent);
            });

            // ✅ Clicking on car name shows a Toast
            carName.setOnClickListener(view1 ->
                    Toast.makeText(context, carNames[position], Toast.LENGTH_SHORT).show());

            // ✅ Clicking on car image shows a Toast
            carPhoto.setOnClickListener(view12 ->
                    Toast.makeText(context, carNames[position], Toast.LENGTH_SHORT).show());

            return view;
        }
    }
}
