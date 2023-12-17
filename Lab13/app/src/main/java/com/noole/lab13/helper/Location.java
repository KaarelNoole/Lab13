package com.noole.lab13.helper;

import androidx.annotation.NonNull;

public class Location {
    public double latitude;
    public double longitude;
    public String date;

    @NonNull
    @Override
    public String toString() {
        return "Date: " + date + "\nLatitude: " + latitude + "\nLongitude: " + longitude;
    }
}
