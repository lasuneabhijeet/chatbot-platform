package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.security.JwtUtils; // Ensure this matches your package
import com.example.demo.dto.AuthResponse; // Ensure this matches your package
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    // Spring will automatically inject both beans here
    public AuthController(UserService userService, JwtUtils jwtUtils,PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        // 1. Find the user
        User user = userService.findByEmail(loginRequest.getEmail());

        // 2. USE THE ENCODER TO COMPARE (The key fix!)
        // Do NOT use user.getPassword().equals(loginRequest.getPassword())
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            
            // 3. Generate token if matcsh is found
            String token = jwtUtils.generateToken(user.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        }

        // 4. If it reaches here, it failed
        return ResponseEntity.status(401).body("Invalid email or password");
    }
}