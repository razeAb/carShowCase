package com.example.carshowcase;
import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.*;

public class MainActivity extends AppCompatActivity {

    ListView carsList;
    ArrayList<Car> carList = new ArrayList<>();
    CarAdapter adapter;
    DatabaseReference carsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Car Showcase");

        // Firebase DB Reference
        carsRef = FirebaseDatabase.getInstance().getReference("cars");

        // ListView and adapter setup
        carsList = findViewById(R.id.myList);
        adapter = new CarAdapter(this, carList);
        carsList.setAdapter(adapter);

        // Fetch from Firebase
        fetchCarData();
    }

    private void fetchCarData() {
        carsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                carList.clear();
                for (DataSnapshot carSnap : snapshot.getChildren()) {
                    Car car = carSnap.getValue(Car.class);
                    if (car != null) carList.add(car);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to load cars", Toast.LENGTH_SHORT).show();
                Log.e("Firebase", "Error: ", error.toException());
            }
        });
    }

    // Toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Adapter
    class CarAdapter extends BaseAdapter {
        Context context;
        ArrayList<Car> cars;
        LayoutInflater inflater;

        CarAdapter(Context context, ArrayList<Car> cars) {
            this.context = context;
            this.cars = cars;
            this.inflater = LayoutInflater.from(context);
        }

        @Override public int getCount() { return cars.size(); }
        @Override public Object getItem(int i) { return cars.get(i); }
        @Override public long getItemId(int i) { return i; }

        class ViewHolder {
            TextView carId, carName;
            ImageView carImage;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder();
                holder.carId = convertView.findViewById(R.id.carid);
                holder.carName = convertView.findViewById(R.id.carName);
                holder.carImage = convertView.findViewById(R.id.carImage);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Car car = cars.get(i);
            holder.carId.setText("ID: " + car.id);
            holder.carName.setText(car.name);

            int imgResId = getResources().getIdentifier(car.image.toLowerCase(), "drawable", getPackageName());
            if (imgResId != 0) {
                holder.carImage.setImageResource(imgResId);
            } else {
                holder.carImage.setImageResource(R.drawable.placeholder); // optional fallback image
            }

            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(context, CarDetailActivity.class);
                intent.putExtra("car_name", car.name);
                intent.putExtra("car_image", imgResId);
                intent.putExtra("car_description", car.description);
                context.startActivity(intent);
            });

            return convertView;
        }
    }
}
