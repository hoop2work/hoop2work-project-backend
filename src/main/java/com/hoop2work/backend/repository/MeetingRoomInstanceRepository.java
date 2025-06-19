package com.hoop2work.backend.repository;

import com.hoop2work.backend.model.MeetingRoomInstance;
import com.hoop2work.backend.model.PredefinedMeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRoomInstanceRepository  extends JpaRepository<MeetingRoomInstance, Long> {
    @Query("SELECT m FROM MeetingRoomInstance m WHERE m.meetingRoom.id = :roomId " +
            "AND m.bookingEnd > :start AND m.bookingStart < :end")
    List<MeetingRoomInstance> findOverlappingBookings(
            @Param("roomId") Long roomId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    List<MeetingRoomInstance> findByTripInstanceId(Long tripId);

}
