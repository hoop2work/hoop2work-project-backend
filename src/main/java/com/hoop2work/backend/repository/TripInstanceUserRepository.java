package com.hoop2work.backend.repository;

import com.hoop2work.backend.model.TripInstanceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripInstanceUserRepository extends JpaRepository<TripInstanceUser, Long> {
    List<TripInstanceUser> findByTripInstanceId(Long tripId);
    List<TripInstanceUser> findByUserId(Long userId);
    Optional<TripInstanceUser> findByTripInstanceIdAndUserId(Long tripId, Long userId);
}
