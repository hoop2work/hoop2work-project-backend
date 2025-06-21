package com.hoop2work.backend.controller;

import com.hoop2work.backend.model.MeetingRoomInstance;
import com.hoop2work.backend.model.PredefinedMeetingRoom;
import com.hoop2work.backend.model.Team;
import com.hoop2work.backend.repository.PredefinedMeetingRoomRepository;
import com.hoop2work.backend.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meeting-room-instances")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService meetingRoomService;

    @PostMapping
    public ResponseEntity<?> createMeetingRoomInstance(@RequestBody Map<String, Object> request) {
        try {
            MeetingRoomInstance created = meetingRoomService.createMeetingRoomInstance(request);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBookingTimes(@PathVariable Long id, @RequestBody Map<String, String> times) {
        try {
            LocalDateTime start = LocalDateTime.parse(times.get("bookingStart"));
            LocalDateTime end = LocalDateTime.parse(times.get("bookingEnd"));
            MeetingRoomInstance updated = meetingRoomService.updateBookingTimes(id, start, end);
            return ResponseEntity.ok(updated);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Invalid data: " + ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingRoomInstance> getById(@PathVariable Long id) {
        return ResponseEntity.of(meetingRoomService.getById(id));
    }

    @GetMapping("/by-trip/{tripId}")
    public ResponseEntity<List<MeetingRoomInstance>> getByTripId(@PathVariable Long tripId) {
        return ResponseEntity.ok(meetingRoomService.getByTripId(tripId));
    }

    @GetMapping("/predefined")
    public ResponseEntity<List<PredefinedMeetingRoom>> getAllPredefinedMeetingRooms() {
        return ResponseEntity.ok(meetingRoomService.getAllPredefinedMeedingRooms());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        meetingRoomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
