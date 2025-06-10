package com.hoop2work.backend.controller;

import com.hoop2work.backend.model.User;
import com.hoop2work.backend.repository.UserRepository;
import com.hoop2work.backend.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        // Check if username or email already exists
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("message", "Username already in use"));
        }
        if (userRepo.findByUsername(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("message", "Email already in use"));
        }

        // Encode password and save
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("message", "Username and password must be provided"));
        }

        User user = userRepo.findByUsername(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Invalid credentials"));
        }

        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
