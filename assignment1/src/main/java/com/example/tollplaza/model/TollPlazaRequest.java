package com.example.tollplaza.model;

/**
 * Request DTO for toll plaza API.
 * Contains source and destination pincodes.
 */
public class TollPlazaRequest {
    /** Source pincode */
    private String sourcePincode;
    /** Destination pincode */
    private String destinationPincode;

    public String getSourcePincode() { return sourcePincode; }
    public void setSourcePincode(String sourcePincode) { this.sourcePincode = sourcePincode; }
    public String getDestinationPincode() { return destinationPincode; }
    public void setDestinationPincode(String destinationPincode) { this.destinationPincode = destinationPincode; }
} 