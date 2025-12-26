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
    private final JwtTokenProvider jwtTokenProvider; // ✅ injected

    // ✅ Constructor Injection ONLY
    public AuthController(UserAccountService userAccountService,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ================= REGISTER =================
    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(
            @org.springframework.web.bind.annotation.RequestBody UserAccount user) {

        user.setPasswordHash(
                passwordEncoder.encode(user.getPasswordHash())
        );

        return ResponseEntity.ok(userAccountService.register(user));
    }

    // ================= LOGIN =================
    @Operation(summary = "User Login")
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(
                                    example = "{ \"email\": \"ajay@example.com\", \"password\": \"ajay123\" }"
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
