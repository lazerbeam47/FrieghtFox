package com.example.tollplaza.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for pincode geocoding and spatial calculations.
 * Provides static methods to map pincodes to coordinates and check proximity to a route.
 */
public class PincodeGeoUtil {
    private static final Map<String, double[]> PINCODE_MAP = new HashMap<>();
    static {
        // Add a few demo pincodes (Delhi, Bangalore, Pune, etc.)
        PINCODE_MAP.put("110001", new double[]{28.6328, 77.2197}); // Connaught Place, Delhi
        PINCODE_MAP.put("560001", new double[]{12.9766, 77.5993}); // Bangalore
        PINCODE_MAP.put("411045", new double[]{18.5074, 73.8077}); // Pune
        PINCODE_MAP.put("560064", new double[]{13.0845, 77.5555}); // Bangalore North
    }

    /**
     * Returns [lat, lng] for a given pincode, or null if not found.
     */
    public static double[] getLatLngForPincode(String pincode) {
        return PINCODE_MAP.get(pincode);
    }

    /**
     * Checks if a point is within a buffer (in km) of the line between two coordinates.
     */
    public static boolean isPointNearLine(double lat1, double lng1, double lat2, double lng2, double lat, double lng, double bufferKm) {
        // ... existing code ...
        return false; // Placeholder for actual logic
    }
} 