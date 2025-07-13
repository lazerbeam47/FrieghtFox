package com.example.tollplaza.controller;

import com.example.tollplaza.model.TollPlazaRequest;
import com.example.tollplaza.model.TollPlazaResponse;
import com.example.tollplaza.service.TollPlazaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Toll Plaza API.
 * Handles requests to find toll plazas between two pincodes.
 */
@RestController
@RequestMapping("/api/v1/toll-plazas")
public class TollPlazaController {
    private final TollPlazaService tollPlazaService;

    /**
     * Constructor for dependency injection of the service layer.
     * @param tollPlazaService Service to handle toll plaza logic
     */
    public TollPlazaController(TollPlazaService tollPlazaService) {
        this.tollPlazaService = tollPlazaService;
    }

    /**
     * POST endpoint to get toll plazas between two pincodes.
     * @param request Request body containing source and destination pincodes
     * @return List of toll plazas and route info
     */
    @PostMapping
    public ResponseEntity<TollPlazaResponse> getTollPlazas(@RequestBody TollPlazaRequest request) {
        // Delegate to service to process the request
        TollPlazaResponse response = tollPlazaService.findTollPlazasBetweenPincodes(request);
        return ResponseEntity.ok(response);
    }
} 