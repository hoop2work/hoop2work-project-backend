package com.hoop2work.backend.controller;

import com.hoop2work.backend.exception.NotFoundException;
import com.hoop2work.backend.model.MeetingRoomInstance;
import com.hoop2work.backend.model.PredefinedMeetingRoom;
import com.hoop2work.backend.repository.MeetingRoomInstanceRepository;
import com.hoop2work.backend.security.SecuredEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meetingroom-instance")
public class MeetingRoomInstanceController {

    @Autowired
    private MeetingRoomInstanceRepository meetingRoomRepositoryInstance;

    @SecuredEndpoint
    @GetMapping
    public List<MeetingRoomInstance> getAllMeetingRoomsInstance() {
        return meetingRoomRepositoryInstance.findAll();
    }

    @SecuredEndpoint
    @GetMapping("/{id}")
    public MeetingRoomInstance getMeetingRoomInstanceById(@PathVariable Long id) {
        return meetingRoomRepositoryInstance.findById(id)
                .orElseThrow(() -> new NotFoundException("Meeting room instance with ID " + id + " not found"));
    }

}
