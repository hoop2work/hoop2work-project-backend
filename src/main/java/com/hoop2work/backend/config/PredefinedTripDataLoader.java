package com.hoop2work.backend.config;

import com.hoop2work.backend.model.PredefinedTrip;
import com.hoop2work.backend.repository.PredefinedTripRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class PredefinedTripDataLoader {

    @Bean
    public CommandLineRunner loadPredefinedTrips(PredefinedTripRepository repository) {
        return args -> {
            if (repository.count() > 0) return;

            LocalDate today = LocalDate.now();

            List<PredefinedTrip> trips = List.of(
                    new PredefinedTrip("Trip to Berlin", "Office", "Berlin", "Capital of Germany with many historical sights.",
                            LocalDateTime.of(today, LocalTime.of(8, 0)),
                            LocalDateTime.of(today, LocalTime.of(9, 15)),
                            LocalDateTime.of(today, LocalTime.of(17, 30)),
                            LocalDateTime.of(today, LocalTime.of(18, 45)),
                            new BigDecimal("49.99"), "Berlin"),

                    new PredefinedTrip("Trip to Paris", "Office", "Paris", "Romantic city with the Eiffel Tower and French culture.",
                            LocalDateTime.of(today, LocalTime.of(10, 0)),
                            LocalDateTime.of(today, LocalTime.of(11, 40)),
                            LocalDateTime.of(today, LocalTime.of(14, 0)),
                            LocalDateTime.of(today, LocalTime.of(15, 30)),
                            new BigDecimal("69.99"), "Paris"),

                    new PredefinedTrip("Trip to Rome", "Office", "Rome", "Ancient city with the Colosseum and the Vatican.",
                            LocalDateTime.of(today, LocalTime.of(7, 15)),
                            LocalDateTime.of(today, LocalTime.of(9, 45)),
                            LocalDateTime.of(today, LocalTime.of(19, 0)),
                            LocalDateTime.of(today, LocalTime.of(21, 30)),
                            new BigDecimal("59.99"), "Rom"),

                    new PredefinedTrip("Trip to Barcelona", "Office", "Barcelona", "Vibrant city with unique architecture by Gaudí.",
                            LocalDateTime.of(today, LocalTime.of(12, 0)),
                            LocalDateTime.of(today, LocalTime.of(14, 20)),
                            LocalDateTime.of(today, LocalTime.of(16, 45)),
                            LocalDateTime.of(today, LocalTime.of(19, 5)),
                            new BigDecimal("54.99"), "Barcelona"),

                    new PredefinedTrip("Trip to Amsterdam", "Office", "Amsterdam", "Bike-friendly and famous for canals and museums.",
                            LocalDateTime.of(today, LocalTime.of(6, 30)),
                            LocalDateTime.of(today, LocalTime.of(8, 0)),
                            LocalDateTime.of(today, LocalTime.of(20, 15)),
                            LocalDateTime.of(today, LocalTime.of(21, 45)),
                            new BigDecimal("44.99"), "Amsterdam"),

                    new PredefinedTrip("Trip to Vienna", "Office", "Vienna", "Classical music, coffeehouse culture, and imperial history.",
                            LocalDateTime.of(today, LocalTime.of(9, 0)),
                            LocalDateTime.of(today, LocalTime.of(10, 10)),
                            LocalDateTime.of(today, LocalTime.of(15, 30)),
                            LocalDateTime.of(today, LocalTime.of(16, 40)),
                            new BigDecimal("64.99"), "Wien"),

                    new PredefinedTrip("Trip to Prague", "Office", "Prague", "Fairytale old town with lots of history and culture.",
                            LocalDateTime.of(today, LocalTime.of(11, 0)),
                            LocalDateTime.of(today, LocalTime.of(12, 20)),
                            LocalDateTime.of(today, LocalTime.of(18, 10)),
                            LocalDateTime.of(today, LocalTime.of(19, 30)),
                            new BigDecimal("52.99"), "Prag"),

                    new PredefinedTrip("Trip to Lisbon", "Office", "Lisbon", "Hilly capital with viewpoints and tram rides.",
                            LocalDateTime.of(today, LocalTime.of(13, 45)),
                            LocalDateTime.of(today, LocalTime.of(16, 30)),
                            LocalDateTime.of(today, LocalTime.of(10, 0)),
                            LocalDateTime.of(today, LocalTime.of(13, 0)),
                            new BigDecimal("58.99"), "Lissabon"),

                    new PredefinedTrip("Trip to Copenhagen", "Office", "Copenhagen", "Modern Scandinavian city with design, bike culture, and hygge.",
                            LocalDateTime.of(today, LocalTime.of(7, 0)),
                            LocalDateTime.of(today, LocalTime.of(8, 30)),
                            LocalDateTime.of(today, LocalTime.of(19, 45)),
                            LocalDateTime.of(today, LocalTime.of(21, 15)),
                            new BigDecimal("60.00"), "Kopenhagen")
            );

            repository.saveAll(trips);
        };
    }
}
