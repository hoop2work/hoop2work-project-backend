package com.hoop2work.backend.service;

import com.hoop2work.backend.exception.NotFoundException;
import com.hoop2work.backend.model.PredefinedTrip;
import com.hoop2work.backend.repository.PredefinedTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredefinedTripService {

    @Autowired
    private PredefinedTripRepository predefinedTripRepository;

    public List<PredefinedTrip> getAllPredefinedTrips() {
        return predefinedTripRepository.findAll();
    }

    public PredefinedTrip getPredefinedTripById(Long id) {
        return predefinedTripRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trip with ID " + id + " not found"));
    }
}
