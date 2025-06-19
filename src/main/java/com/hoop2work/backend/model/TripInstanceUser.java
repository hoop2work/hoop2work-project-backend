package com.hoop2work.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "trip_instance_user")
public class TripInstanceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_instance_id", nullable = false)
    private TripInstance tripInstance;

    public TripInstanceUser() {}

    public TripInstanceUser(User user, TripInstance tripInstance) {
        this.user = user;
        this.tripInstance = tripInstance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TripInstance getTripInstance() {
        return tripInstance;
    }

    public void setTripInstance(TripInstance tripInstance) {
        this.tripInstance = tripInstance;
    }
}
