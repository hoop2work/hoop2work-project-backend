package com.hoop2work.backend.service;

import com.hoop2work.backend.model.Team;
import com.hoop2work.backend.model.TeamUser;
import com.hoop2work.backend.model.User;
import com.hoop2work.backend.repository.TeamRepository;
import com.hoop2work.backend.repository.TeamUserRepository;
import com.hoop2work.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamUserRepository teamUserRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public List<Team> getTeamsByUserId(Long userId) {
        return teamUserRepository.findByUserId(userId)
                .stream()
                .map(TeamUser::getTeam)
                .collect(Collectors.toList());
    }

    public List<User> getUsersByTeamId(Long teamId) {
        return teamUserRepository.findByTeamId(teamId)
                .stream()
                .map(TeamUser::getUser)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean updateTeamBudget(Long teamId, Long managerId, BigDecimal newBudget) {
        Optional<Team> teamOpt = teamRepository.findById(teamId);
        if (teamOpt.isPresent() && teamOpt.get().getManager().getId().equals(managerId)) {
            Team team = teamOpt.get();
            team.setBudget(newBudget);
            teamRepository.save(team);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean addUserToTeam(Long teamId, Long userId, Long managerId) {
        Optional<Team> teamOpt = teamRepository.findById(teamId);
        Optional<User> userOpt = userRepository.findById(userId);
        if (teamOpt.isPresent() && userOpt.isPresent()) {
            Team team = teamOpt.get();
            if (!team.getManager().getId().equals(managerId) || team.getManager().getId().equals(userId)) {
                return false;
            }

            if (teamUserRepository.findByTeamIdAndUserId(teamId, userId).isEmpty()) {
                TeamUser teamUser = new TeamUser();
                teamUser.setTeam(team);
                teamUser.setUser(userOpt.get());
                teamUserRepository.save(teamUser);
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean removeUserFromTeam(Long teamId, Long userId, Long managerId) {
        Optional<Team> teamOpt = teamRepository.findById(teamId);
        if (teamOpt.isPresent()) {
            Team team = teamOpt.get();
            if (!team.getManager().getId().equals(managerId) || team.getManager().getId().equals(userId)) {
                return false;
            }

            teamUserRepository.findByTeamIdAndUserId(teamId, userId)
                    .ifPresent(teamUserRepository::delete);
            return true;
        }
        return false;
    }
}
