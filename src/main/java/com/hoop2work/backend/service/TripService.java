package com.hoop2work.backend.service;

import com.hoop2work.backend.model.*;
import com.hoop2work.backend.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private PredefinedTripRepository predefinedTripRepository;

    @Autowired
    private TripInstanceRepository tripInstanceRepository;

    @Autowired
    private TripInstanceUserRepository tripInstanceUserRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    // === PredefinedTrip ===

    public List<PredefinedTrip> getAllPredefinedTrips() {
        return predefinedTripRepository.findAll();
    }

    public Optional<PredefinedTrip> getPredefinedTripById(Long id) {
        return predefinedTripRepository.findById(id);
    }

    // === TripInstance ===

    @Transactional
    public Optional<TripInstance> createTripInstance(Long predefinedTripId, Long teamId, LocalDateTime startDate, LocalDateTime endDate) {
        Optional<PredefinedTrip> tripOpt = predefinedTripRepository.findById(predefinedTripId);
        Optional<Team> teamOpt = teamRepository.findById(teamId);

        if (tripOpt.isPresent() && teamOpt.isPresent()) {
            TripInstance tripInstance = new TripInstance(tripOpt.get(), teamOpt.get(), startDate, endDate);
            return Optional.of(tripInstanceRepository.save(tripInstance));
        }

        return Optional.empty();
    }

    public List<TripInstance> getTripInstancesByTeam(Long teamId) {
        return tripInstanceRepository.findByTeamId(teamId);
    }

    public List<TripInstance> getTripInstancesByUser(Long userId) {
        return tripInstanceUserRepository.findByUserId(userId).stream()
                .map(TripInstanceUser::getTripInstance)
                .toList();
    }

    public Optional<TripInstance> getTripInstanceById(Long id) {
        return tripInstanceRepository.findById(id);
    }

    @Transactional
    public Optional<TripInstance> updateTripInstanceDates(Long tripInstanceId, LocalDateTime startDate, LocalDateTime endDate) {
        Optional<TripInstance> tripOpt = tripInstanceRepository.findById(tripInstanceId);
        if (tripOpt.isPresent()) {
            TripInstance trip = tripOpt.get();
            trip.setStartDate(startDate);
            trip.setEndDate(endDate);
            return Optional.of(tripInstanceRepository.save(trip));
        }
        return Optional.empty();
    }

    // === TripInstanceUser ===

    @Transactional
    public boolean addUserToTrip(Long tripId, Long userId) {
        Optional<TripInstance> tripOpt = tripInstanceRepository.findById(tripId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (tripOpt.isPresent() && userOpt.isPresent()) {
            if (tripInstanceUserRepository.findByTripInstanceIdAndUserId(tripId, userId).isEmpty()) {
                TripInstanceUser newUser = new TripInstanceUser(userOpt.get(), tripOpt.get());
                tripInstanceUserRepository.save(newUser);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean removeUserFromTrip(Long tripId, Long userId) {
        Optional<TripInstanceUser> userEntry = tripInstanceUserRepository.findByTripInstanceIdAndUserId(tripId, userId);
        userEntry.ifPresent(tripInstanceUserRepository::delete);
        return userEntry.isPresent();
    }

    public List<User> getUsersForTrip(Long tripId) {
        return tripInstanceUserRepository.findByTripInstanceId(tripId)
                .stream()
                .map(TripInstanceUser::getUser)
                .toList();
    }
}
