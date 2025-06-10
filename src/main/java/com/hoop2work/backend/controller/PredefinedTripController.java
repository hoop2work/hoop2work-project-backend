package com.hoop2work.backend.controller;

import com.hoop2work.backend.model.PredefinedTrip;
import com.hoop2work.backend.repository.PredefinedTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/predefined-trip")
public class PredefinedTripController {

    @Autowired
    private PredefinedTripRepository predefinedTripRepo;

    @GetMapping
    public ResponseEntity<List<PredefinedTrip>> getAllPredefinedTrips() {
        System.out.println("getAllPredefinedTrips");
        List<PredefinedTrip> predefinedTrips = predefinedTripRepo.findAll();
        if (predefinedTrips.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(predefinedTrips, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredefinedTrip> getPredefinedTripById(@PathVariable Long id) {
        Optional<PredefinedTrip> predefinedTrip = predefinedTripRepo.findById(id);
        return predefinedTrip.map(trip -> new ResponseEntity<>(trip, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
