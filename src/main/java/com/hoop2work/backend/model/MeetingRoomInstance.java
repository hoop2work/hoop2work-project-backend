package com.hoop2work.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "meeting_room_instance")
public class MeetingRoomInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "predefined_meeting_room_id", nullable = false)
    private PredefinedMeetingRoom meetingRoom;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_instance_id", nullable = false)
    private TripInstance tripInstance;

    @NotNull
    @Column(name = "booking_start", nullable = false)
    private LocalDateTime bookingStart;

    @NotNull
    @Column(name = "booking_end", nullable = false)
    private LocalDateTime bookingEnd;

    public MeetingRoomInstance() {}

    public MeetingRoomInstance(@NotNull PredefinedMeetingRoom meetingRoom, @NotNull TripInstance tripInstance,
                               @NotNull LocalDateTime bookingStart, @NotNull LocalDateTime bookingEnd) {
        this.meetingRoom = meetingRoom;
        this.tripInstance = tripInstance;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PredefinedMeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(PredefinedMeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public TripInstance getTripInstance() {
        return tripInstance;
    }

    public void setTripInstance(TripInstance tripInstance) {
        this.tripInstance = tripInstance;
    }

    public LocalDateTime getBookingStart() {
        return bookingStart;
    }

    public void setBookingStart(LocalDateTime bookingStart) {
        this.bookingStart = bookingStart;
    }

    public LocalDateTime getBookingEnd() {
        return bookingEnd;
    }

    public void setBookingEnd(LocalDateTime bookingEnd) {
        this.bookingEnd = bookingEnd;
    }
}
