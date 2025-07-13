package com.example.tollplaza.service;

import com.example.tollplaza.exception.ApiException;
import com.example.tollplaza.model.Route;
import com.example.tollplaza.model.TollPlaza;
import com.example.tollplaza.model.TollPlazaRequest;
import com.example.tollplaza.model.TollPlazaResponse;
import com.example.tollplaza.util.PincodeGeoUtil;
import com.example.tollplaza.util.TollPlazaCsvLoader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for toll plaza operations.
 * Handles business logic for finding toll plazas between two pincodes.
 */
@Service
public class TollPlazaService {
    private final List<TollPlaza> tollPlazas;

    /**
     * Loads toll plaza data from CSV at startup.
     */
    public TollPlazaService() {
        // Load all toll plazas from the CSV file into memory
        this.tollPlazas = TollPlazaCsvLoader.loadTollPlazasFromCsv();
    }

    /**
     * Finds toll plazas between two pincodes.
     * Steps:
     * 1. Geocode source and destination pincodes to lat/lng.
     * 2. Find toll plazas within 20km of the straight line between the two points.
     * @param request Request containing source and destination pincodes
     * @return Response with route and matching toll plazas
     */
    public TollPlazaResponse findTollPlazasBetweenPincodes(TollPlazaRequest request) {
        // Geocode pincodes to coordinates
        double[] srcCoords = PincodeGeoUtil.getLatLngForPincode(request.getSourcePincode());
        double[] destCoords = PincodeGeoUtil.getLatLngForPincode(request.getDestinationPincode());

        if (srcCoords == null || destCoords == null) {
            throw new ApiException("Invalid source or destination pincode");
        }

        // Find toll plazas within 20km of the straight line between source and destination
        List<TollPlaza> matchingTollPlazas = new ArrayList<>();
        for (TollPlaza plaza : tollPlazas) {
            if (isWithinCorridor(srcCoords, destCoords, plaza.getLatitude(), plaza.getLongitude(), 20.0)) {
                matchingTollPlazas.add(plaza);
            }
        }

        // Build and return the response
        Route route = new Route(request.getSourcePincode(), request.getDestinationPincode(), srcCoords, destCoords);
        return new TollPlazaResponse(route, matchingTollPlazas);
    }

    private void validateRequest(TollPlazaRequest request) {
        String src = request.getSourcePincode();
        String dest = request.getDestinationPincode();
        if (src == null || dest == null || !src.matches("\\d{6}") || !dest.matches("\\d{6}")) {
            throw new ApiException("Invalid source or destination pincode");
        }
        if (src.equals(dest)) {
            throw new ApiException("Source and destination pincodes cannot be the same");
        }
    }

    // Haversine formula to calculate distance between two lat/lng points in km
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // Distance from point C to line AB (in km)
    private double distanceToLine(double[] A, double[] B, double[] C) {
        double lat1 = Math.toRadians(A[0]), lon1 = Math.toRadians(A[1]);
        double lat2 = Math.toRadians(B[0]), lon2 = Math.toRadians(B[1]);
        double lat3 = Math.toRadians(C[0]), lon3 = Math.toRadians(C[1]);
        // Convert to Cartesian coordinates
        double[] p1 = {Math.cos(lat1) * Math.cos(lon1), Math.cos(lat1) * Math.sin(lon1), Math.sin(lat1)};
        double[] p2 = {Math.cos(lat2) * Math.cos(lon2), Math.cos(lat2) * Math.sin(lon2), Math.sin(lat2)};
        double[] p3 = {Math.cos(lat3) * Math.cos(lon3), Math.cos(lat3) * Math.sin(lon3), Math.sin(lat3)};
        double[] p12 = {p2[0] - p1[0], p2[1] - p1[1], p2[2] - p1[2]};
        double[] p13 = {p3[0] - p1[0], p3[1] - p1[1], p3[2] - p1[2]};
        double[] cross = {p12[1]*p13[2] - p12[2]*p13[1], p12[2]*p13[0] - p12[0]*p13[2], p12[0]*p13[1] - p12[1]*p13[0]};
        double area = Math.sqrt(cross[0]*cross[0] + cross[1]*cross[1] + cross[2]*cross[2]);
        double base = Math.sqrt(p12[0]*p12[0] + p12[1]*p12[1] + p12[2]*p12[2]);
        double distance = area / base;
        return distance * 6371; // Convert to km
    }

    /**
     * Checks if a point (lat, lng) is within a corridor (buffer) around the straight line between two points.
     * Uses the perpendicular distance from the point to the line segment.
     * @param src Source coordinates
     * @param dest Destination coordinates
     * @param lat Latitude of the point
     * @param lng Longitude of the point
     * @param bufferKm Buffer distance in kilometers
     * @return true if within buffer, false otherwise
     */
    private boolean isWithinCorridor(double[] src, double[] dest, double lat, double lng, double bufferKm) {
        // Haversine and geometry logic to check corridor
        return PincodeGeoUtil.isPointNearLine(src[0], src[1], dest[0], dest[1], lat, lng, bufferKm);
    }
} 