package com.example.tollplaza.model;

/**
 * Model representing a route between two pincodes.
 */
public class Route {
    /** Source pincode */
    private String sourcePincode;
    /** Destination pincode */
    private String destinationPincode;
    /** Source coordinates [lat, lng] */
    private double[] sourceCoords;
    /** Destination coordinates [lat, lng] */
    private double[] destinationCoords;
    private int distanceInKm;

    public Route(String sourcePincode, String destinationPincode, int distanceInKm) {
        this.sourcePincode = sourcePincode;
        this.destinationPincode = destinationPincode;
        this.distanceInKm = distanceInKm;
    }

    public String getSourcePincode() { return sourcePincode; }
    public void setSourcePincode(String sourcePincode) { this.sourcePincode = sourcePincode; }
    public String getDestinationPincode() { return destinationPincode; }
    public void setDestinationPincode(String destinationPincode) { this.destinationPincode = destinationPincode; }
    public int getDistanceInKm() { return distanceInKm; }
    public void setDistanceInKm(int distanceInKm) { this.distanceInKm = distanceInKm; }
} 