package com.hoop2work.backend.controller;

import com.hoop2work.backend.exception.NotFoundException;
import com.hoop2work.backend.model.PredefinedMeetingRoom;
import com.hoop2work.backend.repository.PredefinedMeetingRoomRepository;
import com.hoop2work.backend.security.SecuredEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meetingrooms")
public class MeetingRoomInstanceController {

    @Autowired
    private PredefinedMeetingRoomRepository predefinedMeetingRoomRepo;

    @GetMapping
    public List<PredefinedMeetingRoom> getAllPredefinedMeetingRooms() {
        return predefinedMeetingRoomRepo.findAll();
    }

    @SecuredEndpoint
    @GetMapping("/{id}")
    public PredefinedMeetingRoom getPredefinedMeetingRoomById(@PathVariable Long id) {
        Optional<PredefinedMeetingRoom> optionalRoom = predefinedMeetingRoomRepo.findById(id);
        if (optionalRoom.isEmpty()) {
            throw new NotFoundException("Meeting room with ID " + id + " not found");
        }
        PredefinedMeetingRoom room = optionalRoom.get();
        return room;
    }
}
