package com.example.tollplaza.util;

import com.example.tollplaza.model.TollPlaza;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to load toll plaza data from a CSV file at startup.
 */
public class TollPlazaCsvLoader {
    private final List<TollPlaza> tollPlazas = new ArrayList<>();

    @PostConstruct
    public void loadCsv() {
        try {
            ClassPathResource resource = new ClassPathResource("toll_plaza_india.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;
                TollPlaza plaza = new TollPlaza();
                plaza.setLongitude(Double.parseDouble(parts[0]));
                plaza.setLatitude(Double.parseDouble(parts[1]));
                plaza.setName(parts[2]);
                // distanceFromSource will be set later
                tollPlazas.add(plaza);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load toll plaza CSV", e);
        }
    }

    public List<TollPlaza> getTollPlazas() {
        return tollPlazas;
    }

    /**
     * Loads toll plazas from the CSV file in resources.
     * @return List of TollPlaza objects
     */
    public static List<TollPlaza> loadTollPlazasFromCsv() {
        // ... existing code ...
    }
} 