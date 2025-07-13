package com.example.tollplaza.model;

public class TollPlaza {
    private String name;
    private double latitude;
    private double longitude;
    private int distanceFromSource;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public int getDistanceFromSource() { return distanceFromSource; }
    public void setDistanceFromSource(int distanceFromSource) { this.distanceFromSource = distanceFromSource; }
} 