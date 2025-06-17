package com.hoop2work.backend.controller;

import com.hoop2work.backend.model.PredefinedTrip;
import com.hoop2work.backend.security.SecuredEndpoint;
import com.hoop2work.backend.service.PredefinedTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predefined-trips")
public class PredefinedTripController {

    @Autowired
    private PredefinedTripService predefinedTripService;

    @SecuredEndpoint
    @GetMapping
    public List<PredefinedTrip> getAllPredefinedTrips() {
        return predefinedTripService.getAllPredefinedTrips();
    }

    @SecuredEndpoint
    @GetMapping("/{id}")
    public PredefinedTrip getPredefinedTripById(@PathVariable Long id) {
        return predefinedTripService.getPredefinedTripById(id);
    }
}
