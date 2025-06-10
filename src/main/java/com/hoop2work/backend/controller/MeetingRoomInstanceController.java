package com.hoop2work.backend.controller;

import com.hoop2work.backend.model.PredefinedMeetingRoom;
import com.hoop2work.backend.repository.PredefinedMeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meetingrooms")  // Optional: define a base URL
public class MeetingRoomInstanceController {

    @Autowired
    private PredefinedMeetingRoomRepository predefinedMeetingRoomRepo;

    @GetMapping
    public ResponseEntity<List<PredefinedMeetingRoom>> getAllPredefinedMeetingRooms() {
        System.out.println("getAllPredefinedMeetingRooms");
        List<PredefinedMeetingRoom> predefinedMeetingRooms = predefinedMeetingRoomRepo.findAll();
        if (predefinedMeetingRooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(predefinedMeetingRooms, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredefinedMeetingRoom> getPredefinedMeetingRoomById(@PathVariable Long id) {
        Optional<PredefinedMeetingRoom> predefinedMeetingRoom = predefinedMeetingRoomRepo.findById(id);
        return predefinedMeetingRoom
                .map(room -> new ResponseEntity<>(room, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
