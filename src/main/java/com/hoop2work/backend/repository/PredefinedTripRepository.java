package com.hoop2work.backend.repository;

import com.hoop2work.backend.model.PredefinedTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredefinedTripRepository extends JpaRepository<PredefinedTrip, Long> {
}
