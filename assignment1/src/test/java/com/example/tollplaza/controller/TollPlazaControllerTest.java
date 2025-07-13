package com.example.tollplaza.controller;

import com.example.tollplaza.model.TollPlazaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TollPlazaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testValidRequest() throws Exception {
        TollPlazaRequest req = new TollPlazaRequest();
        req.setSourcePincode("110001");
        req.setDestinationPincode("560001");
        mockMvc.perform(post("/api/v1/toll-plazas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.route.sourcePincode").value("110001"))
                .andExpect(jsonPath("$.route.destinationPincode").value("560001"))
                .andExpect(jsonPath("$.tollPlazas").isArray());
    }

    @Test
    void testInvalidPincode() throws Exception {
        TollPlazaRequest req = new TollPlazaRequest();
        req.setSourcePincode("1100");
        req.setDestinationPincode("560001");
        mockMvc.perform(post("/api/v1/toll-plazas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid source or destination pincode"));
    }

    @Test
    void testSameSourceAndDestination() throws Exception {
        TollPlazaRequest req = new TollPlazaRequest();
        req.setSourcePincode("110001");
        req.setDestinationPincode("110001");
        mockMvc.perform(post("/api/v1/toll-plazas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Source and destination pincodes cannot be the same"));
    }
} 