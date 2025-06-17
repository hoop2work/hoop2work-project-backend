package com.hoop2work.backend.repository;

import com.hoop2work.backend.model.MeetingRoomInstance;
import com.hoop2work.backend.model.PredefinedMeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRoomInstanceRepository  extends JpaRepository<MeetingRoomInstance, Long> {
}
