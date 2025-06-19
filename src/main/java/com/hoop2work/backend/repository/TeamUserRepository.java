package com.hoop2work.backend.repository;

import com.hoop2work.backend.model.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {

    List<TeamUser> findByUserId(Long userId);

    List<TeamUser> findByTeamId(Long teamId);

    Optional<TeamUser> findByTeamIdAndUserId(Long teamId, Long userId);
}
