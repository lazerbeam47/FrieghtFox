package com.example.tollplaza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Model representing a toll plaza.
 */
@Entity
public class TollPlaza {
    /** Name of the toll plaza */
    private String name;
    /** Highway on which the toll plaza is located */
    private String highway;
    /** Latitude of the toll plaza */
    private double latitude;
    /** Longitude of the toll plaza */
    private double longitude;
    private int distanceFromSource;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public int getDistanceFromSource() { return distanceFromSource; }
    public void setDistanceFromSource(int distanceFromSource) { this.distanceFromSource = distanceFromSource; }
} 