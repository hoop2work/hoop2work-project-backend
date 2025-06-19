package com.hoop2work.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "predefined_meeting_room")
public class PredefinedMeetingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "meeting_room_name", nullable = false, length = 255)
    private String meetingRoomName;

    @NotBlank
    @Size(max = 255)
    @Column(name = "city", nullable = false, length = 255)
    private String city;

    @NotNull
    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @NotBlank
    @Size(max = 255)
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    public PredefinedMeetingRoom() {}

    public PredefinedMeetingRoom(@NotBlank @Size(max = 255) String meetingRoomName, @NotBlank @Size(max = 255) String city,
                                 @NotNull Integer availableSeats, @NotBlank @Size(max = 255) String address) {
        this.meetingRoomName = meetingRoomName;
        this.city = city;
        this.availableSeats = availableSeats;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeetingRoomName() {
        return meetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
