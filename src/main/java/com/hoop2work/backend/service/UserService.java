package com.hoop2work.backend.service;

import com.hoop2work.backend.model.User;
import com.hoop2work.backend.repository.UserRepository;
import com.hoop2work.backend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Map<String, String> register(User user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return Map.of("error", "Username already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return Map.of("message", "User registered successfully");
    }

    public Map<String, String> login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return Map.of("error", "Invalid credentials");
        }
        String token = jwtService.generateToken(username);
        return Map.of("token", token);
    }

    public User getUserFromToken(String token) {
        String username = jwtService.extractUsername(token);
        if (username == null) {
            return null;
        }
        return userRepo.findByUsername(username);
    }
}
