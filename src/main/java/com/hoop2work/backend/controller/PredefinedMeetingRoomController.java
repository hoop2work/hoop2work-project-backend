package com.hoop2work.backend.controller;

import com.hoop2work.backend.model.PredefinedMeetingRoom;
import com.hoop2work.backend.security.SecuredEndpoint;
import com.hoop2work.backend.service.PredefinedMeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predefined-meetingrooms")
public class PredefinedMeetingRoomController {

    @Autowired
    private PredefinedMeetingRoomService predefinedMeetingRoomService;

    @SecuredEndpoint
    @GetMapping
    public List<PredefinedMeetingRoom> getAllPredefinedMeetingRooms() {
        return predefinedMeetingRoomService.getAllPredefinedMeetingRooms();
    }

    @SecuredEndpoint
    @GetMapping("/{id}")
    public PredefinedMeetingRoom getPredefinedMeetingRoomById(@PathVariable Long id) {
        return predefinedMeetingRoomService.getPredefinedMeetingRoomById(id);
    }
}
