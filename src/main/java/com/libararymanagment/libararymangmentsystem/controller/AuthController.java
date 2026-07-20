package com.libararymanagment.libararymangmentsystem.controller;

import com.libararymanagment.libararymangmentsystem.dto.AuthResponse;
import com.libararymanagment.libararymangmentsystem.dto.LoginRequest;
import com.libararymanagment.libararymangmentsystem.dto.UserRequest;
import com.libararymanagment.libararymangmentsystem.entity.User;
import com.libararymanagment.libararymangmentsystem.repository.UserRepository;
import com.libararymanagment.libararymangmentsystem.service.JwtService;
import com.libararymanagment.libararymangmentsystem.service.CustomUserDetailsService;
import com.libararymanagment.libararymangmentsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(
            UserService userService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            CustomUserDetailsService userDetailsService
    ) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    // SIGN UP (REGISTER)
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserRequest request) {
        User savedUser = userService.saveUser(request);
        return ResponseEntity.ok(savedUser);
    }

    // LOGIN (SIGN IN) - Generates JWT Token
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(userDetails);

        AuthResponse response = new AuthResponse(
                token,
                user.getEmail(),
                user.getUsername(),
                user.getRole().name()
        );

        return ResponseEntity.ok(response);
    }
}
