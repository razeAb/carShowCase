package com.example.carshowcase;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Car {
    public String id;
    public String name;
    public String description;
    public String image;

    // Required empty constructor for Firebase
    public Car() {}

    public Car(String id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
