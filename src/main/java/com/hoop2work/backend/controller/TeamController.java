package com.hoop2work.backend.controller;

import com.hoop2work.backend.model.Team;
import com.hoop2work.backend.model.User;
import com.hoop2work.backend.service.TeamService;
import com.hoop2work.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    // Get a specific team by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Optional<Team> team = teamService.getTeamById(id);
        return team.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    // Get all teams for a specific user (public)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Team>> getTeamsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(teamService.getTeamsByUserId(userId));
    }

    // Get all users in a team
    @GetMapping("/{teamId}/users")
    public ResponseEntity<List<User>> getUsersByTeamId(@PathVariable Long teamId) {
        return ResponseEntity.ok(teamService.getUsersByTeamId(teamId));
    }

    // Add user to team
    @PostMapping("/{teamId}/users/{userId}")
    public ResponseEntity<?> addUserToTeam(@PathVariable Long teamId,
                                           @PathVariable Long userId,
                                           @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Missing or invalid Authorization header"));
        }

        String token = authHeader.substring(7);
        User manager = userService.getUserFromToken(token);

        if (manager == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Invalid or expired token"));
        }

        boolean success = teamService.addUserToTeam(teamId, userId, manager.getId());
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // Remove user from team
    @DeleteMapping("/{teamId}/users/{userId}")
    public ResponseEntity<?> removeUserFromTeam(@PathVariable Long teamId,
                                                @PathVariable Long userId,
                                                @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Missing or invalid Authorization header"));
        }

        String token = authHeader.substring(7);
        User manager = userService.getUserFromToken(token);

        if (manager == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Invalid or expired token"));
        }

        boolean success = teamService.removeUserFromTeam(teamId, userId, manager.getId());
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
