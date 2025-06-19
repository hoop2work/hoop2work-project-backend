package com.hoop2work.backend.repository;

import com.hoop2work.backend.model.TripInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripInstanceRepository extends JpaRepository<TripInstance, Long> {
    List<TripInstance> findByTeamId(Long teamId);
}
