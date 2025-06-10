package com.hoop2work.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "predefined_trip")
public class PredefinedTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String title;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String home;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String destination;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String description;

    @NotNull
    @Column(name = "departure_time_destination", nullable = false)
    private LocalDateTime departureTimeDestination;

    @NotNull
    @Column(name = "arrival_time_destination", nullable = false)
    private LocalDateTime arrivalTimeDestination;

    @NotNull
    @Column(name = "departure_time_home", nullable = false)
    private LocalDateTime departureTimeHome;

    @NotNull
    @Column(name = "arrival_time_home", nullable = false)
    private LocalDateTime arrivalTimeHome;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotBlank
    @Size(max = 255)
    @Column(name = "picture_name", nullable = false, length = 255)
    private String pictureName;

    public PredefinedTrip() {}

    public PredefinedTrip(@NotBlank @Size(max = 255) String title, @NotBlank @Size(max = 255) String home,
                          @NotBlank @Size(max = 255) String destination, @NotBlank @Size(max = 255) String description,
                          @NotNull LocalDateTime departureTimeDestination, @NotNull LocalDateTime arrivalTimeDestination,
                          @NotNull LocalDateTime departureTimeHome, @NotNull LocalDateTime arrivalTimeHome,
                          @NotNull BigDecimal price, @NotBlank @Size(max = 255) String pictureName) {
        this.title = title;
        this.home = home;
        this.destination = destination;
        this.description = description;
        this.departureTimeDestination = departureTimeDestination;
        this.arrivalTimeDestination = arrivalTimeDestination;
        this.departureTimeHome = departureTimeHome;
        this.arrivalTimeHome = arrivalTimeHome;
        this.price = price;
        this.pictureName = pictureName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDepartureTimeDestination() {
        return departureTimeDestination;
    }

    public void setDepartureTimeDestination(LocalDateTime departureTimeDestination) {
        this.departureTimeDestination = departureTimeDestination;
    }

    public LocalDateTime getArrivalTimeDestination() {
        return arrivalTimeDestination;
    }

    public void setArrivalTimeDestination(LocalDateTime arrivalTimeDestination) {
        this.arrivalTimeDestination = arrivalTimeDestination;
    }

    public LocalDateTime getDepartureTimeHome() {
        return departureTimeHome;
    }

    public void setDepartureTimeHome(LocalDateTime departureTimeHome) {
        this.departureTimeHome = departureTimeHome;
    }

    public LocalDateTime getArrivalTimeHome() {
        return arrivalTimeHome;
    }

    public void setArrivalTimeHome(LocalDateTime arrivalTimeHome) {
        this.arrivalTimeHome = arrivalTimeHome;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
}
