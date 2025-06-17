package com.hoop2work.backend.service;

import com.hoop2work.backend.exception.NotFoundException;
import com.hoop2work.backend.model.PredefinedMeetingRoom;
import com.hoop2work.backend.repository.PredefinedMeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredefinedMeetingRoomService {

    @Autowired
    private PredefinedMeetingRoomRepository predefinedMeetingRoomRepo;

    public List<PredefinedMeetingRoom> getAllPredefinedMeetingRooms() {
        return predefinedMeetingRoomRepo.findAll();
    }

    public PredefinedMeetingRoom getPredefinedMeetingRoomById(Long id) {
        return predefinedMeetingRoomRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Meeting room with ID " + id + " not found"));
    }
}
