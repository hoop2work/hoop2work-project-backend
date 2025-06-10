package com.hoop2work.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trip_instance")
public class TripInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Optional: Explicitly name the ID column
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "predefined_trip_id", nullable = false)
    private PredefinedTrip predefinedTrip;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    public TripInstance() {}

    public TripInstance(@NotNull PredefinedTrip predefinedTrip, @NotNull LocalDateTime startDate, @NotNull LocalDateTime endDate) {
        this.predefinedTrip = predefinedTrip;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PredefinedTrip getPredefinedTrip() {
        return predefinedTrip;
    }

    public void setPredefinedTrip(PredefinedTrip predefinedTrip) {
        this.predefinedTrip = predefinedTrip;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
