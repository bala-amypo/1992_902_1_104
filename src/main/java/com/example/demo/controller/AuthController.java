package com.example.demo.controller;

import com.example.demo.model.UserAccount;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    // JwtTokenProvider is NOT a Spring bean (test-safe)
    private final JwtTokenProvider jwtTokenProvider =
            new JwtTokenProvider(
                    "ChangeThisSecretKeyForJwt123456789012345",
                    3600000
            );

    // Constructor injection
    public AuthController(UserAccountService userAccountService,
                          PasswordEncoder passwordEncoder) {
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // REGISTER
    // =========================
    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(
            @org.springframework.web.bind.annotation.RequestBody UserAccount user) {

        // passwordHash temporarily holds plain password
        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash())
        );

        UserAccount savedUser = userAccountService.register(user);
        return ResponseEntity.ok(savedUser);
    }

    // =========================
    // LOGIN
    // =========================
    @Operation(
            summary = "User Login",
            description = "Login using email and password"
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(
                                    example = "{ \"email\": \"ajay@example.com\", \"password\": \"password123\" }"
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody
            Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        UserAccount user = userAccountService.findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
