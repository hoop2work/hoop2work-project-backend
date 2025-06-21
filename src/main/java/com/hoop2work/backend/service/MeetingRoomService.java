package com.hoop2work.backend.service;

import com.hoop2work.backend.model.MeetingRoomInstance;
import com.hoop2work.backend.model.PredefinedMeetingRoom;
import com.hoop2work.backend.model.Team;
import com.hoop2work.backend.model.TripInstance;
import com.hoop2work.backend.repository.MeetingRoomInstanceRepository;
import com.hoop2work.backend.repository.PredefinedMeetingRoomRepository;
import com.hoop2work.backend.repository.TripInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MeetingRoomService {

    @Autowired
    private MeetingRoomInstanceRepository meetingRoomInstanceRepository;

    @Autowired
    private PredefinedMeetingRoomRepository predefinedMeetingRoomRepository;

    @Autowired
    private TripInstanceRepository tripInstanceRepository;

    public MeetingRoomInstance createMeetingRoomInstance(Map<String, Object> request) {
        Long roomId = ((Number) request.get("predefinedMeetingRoomId")).longValue();
        Long tripId = ((Number) request.get("tripInstanceId")).longValue();
        LocalDateTime bookingStart = LocalDateTime.parse((String) request.get("bookingStart"));
        LocalDateTime bookingEnd = LocalDateTime.parse((String) request.get("bookingEnd"));

        PredefinedMeetingRoom room = predefinedMeetingRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Meeting room not found"));
        TripInstance trip = tripInstanceRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Trip instance not found"));

        // Check for overlapping bookings
        List<MeetingRoomInstance> overlapping = meetingRoomInstanceRepository
                .findOverlappingBookings(roomId, bookingStart, bookingEnd);
        if (!overlapping.isEmpty()) {
            throw new IllegalArgumentException("Meeting room is already booked for the given time.");
        }

        MeetingRoomInstance instance = new MeetingRoomInstance(room, trip, bookingStart, bookingEnd);
        return meetingRoomInstanceRepository.save(instance);
    }

    public MeetingRoomInstance updateBookingTimes(Long id, LocalDateTime newStart, LocalDateTime newEnd) {
        MeetingRoomInstance instance = meetingRoomInstanceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Instance not found"));

        // Check for overlapping bookings excluding this instance
        List<MeetingRoomInstance> overlapping = meetingRoomInstanceRepository
                .findOverlappingBookings(instance.getMeetingRoom().getId(), newStart, newEnd);
        overlapping.removeIf(i -> i.getId().equals(id));
        if (!overlapping.isEmpty()) {
            throw new IllegalArgumentException("New time overlaps with another booking.");
        }

        instance.setBookingStart(newStart);
        instance.setBookingEnd(newEnd);
        return meetingRoomInstanceRepository.save(instance);
    }

    public Optional<MeetingRoomInstance> getById(Long id) {
        return meetingRoomInstanceRepository.findById(id);
    }

    public List<MeetingRoomInstance> getByTripId(Long tripId) {
        return meetingRoomInstanceRepository.findByTripInstanceId(tripId);
    }

    public List<PredefinedMeetingRoom> getAllPredefinedMeedingRooms() {
        return predefinedMeetingRoomRepository.findAll();
    }

    public void delete(Long id) {
        meetingRoomInstanceRepository.deleteById(id);
    }
}
