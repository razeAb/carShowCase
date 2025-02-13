package com.example.carshowcase;

import java.io.Serializable;


public class car implements Serializable {

    private String name;
    private int imageResource;
    private String description;


    public car(String name, int imageResource, String description) {
        this.name = name;
        this.imageResource = imageResource;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }


    public String getDescription() {
        return description;
    }

}
