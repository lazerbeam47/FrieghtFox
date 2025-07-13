package com.example.tollplaza.model;

import java.util.List;

/**
 * Response DTO for toll plaza API.
 * Contains the route and list of toll plazas found.
 */
public class TollPlazaResponse {
    /** Route information */
    private Route route;
    /** List of toll plazas on the route */
    private List<TollPlaza> tollPlazas;

    public TollPlazaResponse(Route route, List<TollPlaza> tollPlazas) {
        this.route = route;
        this.tollPlazas = tollPlazas;
    }

    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }
    public List<TollPlaza> getTollPlazas() { return tollPlazas; }
    public void setTollPlazas(List<TollPlaza> tollPlazas) { this.tollPlazas = tollPlazas; }
} 