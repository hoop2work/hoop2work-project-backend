package com.hoop2work.backend.controller;

import com.hoop2work.backend.model.PredefinedTrip;
import com.hoop2work.backend.model.TripInstance;
import com.hoop2work.backend.model.User;
import com.hoop2work.backend.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    // === PredefinedTrip ===

    @GetMapping("/predefined")
    public ResponseEntity<List<PredefinedTrip>> getAllPredefinedTrips() {
        return ResponseEntity.ok(tripService.getAllPredefinedTrips());
    }

    @GetMapping("/predefined/{id}")
    public ResponseEntity<PredefinedTrip> getPredefinedTripById(@PathVariable Long id) {
        return tripService.getPredefinedTripById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // === TripInstance ===

    @PostMapping("/instances")
    public ResponseEntity<TripInstance> createTripInstance(@RequestBody Map<String, Object> request) {
        Long predefinedTripId = Long.valueOf(request.get("predefinedTripId").toString());
        Long teamId = Long.valueOf(request.get("teamId").toString());
        LocalDateTime startDate = LocalDateTime.parse(request.get("startDate").toString());
        LocalDateTime endDate = LocalDateTime.parse(request.get("endDate").toString());

        return tripService.createTripInstance(predefinedTripId, teamId, startDate, endDate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/instances/team/{teamId}")
    public ResponseEntity<List<TripInstance>> getTripsByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(tripService.getTripInstancesByTeam(teamId));
    }

    @GetMapping("/instances/user/{userId}")
    public ResponseEntity<List<TripInstance>> getTripsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(tripService.getTripInstancesByUser(userId));
    }

    @GetMapping("/instances/{id}")
    public ResponseEntity<TripInstance> getTripInstanceById(@PathVariable Long id) {
        return tripService.getTripInstanceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/instances/{id}")
    public ResponseEntity<TripInstance> updateTripDates(@PathVariable Long id, @RequestBody Map<String, String> request) {
        LocalDateTime startDate = LocalDateTime.parse(request.get("startDate"));
        LocalDateTime endDate = LocalDateTime.parse(request.get("endDate"));

        return tripService.updateTripInstanceDates(id, startDate, endDate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // === TripInstanceUser ===

    @PostMapping("/instances/{tripId}/users/{userId}")
    public ResponseEntity<?> addUserToTrip(@PathVariable Long tripId, @PathVariable Long userId) {
        boolean success = tripService.addUserToTrip(tripId, userId);
        return success ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/instances/{tripId}/users/{userId}")
    public ResponseEntity<?> removeUserFromTrip(@PathVariable Long tripId, @PathVariable Long userId) {
        boolean success = tripService.removeUserFromTrip(tripId, userId);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/instances/{tripId}/users")
    public ResponseEntity<List<User>> getUsersForTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(tripService.getUsersForTrip(tripId));
    }
}
