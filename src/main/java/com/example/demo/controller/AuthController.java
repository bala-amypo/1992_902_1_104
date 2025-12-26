package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.UserAccount;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    // 🔑 Manually created (NOT a Spring bean – test safe)
    private final JwtTokenProvider jwtTokenProvider =
            new JwtTokenProvider(
                    "ChangeThisSecretKeyForJwt123456789012345",
                    3600000
            );

    // ✅ Constructor injection
    public AuthController(UserAccountService userAccountService,
                          PasswordEncoder passwordEncoder) {
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // REGISTER
    // =========================
    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {

        // passwordHash temporarily contains plain password
        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash())
        );

        UserAccount savedUser = userAccountService.register(user);
        return ResponseEntity.ok(savedUser);
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();

        UserAccount user = userAccountService.findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
