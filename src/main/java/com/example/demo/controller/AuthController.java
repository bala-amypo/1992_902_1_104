package com.example.demo.controller;

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

    // Manually created (NOT Spring bean – test safe)
    private final JwtTokenProvider jwtTokenProvider =
            new JwtTokenProvider(
                    "ChangeThisSecretKeyForJwt123456789012345",
                    3600000
            );

    public AuthController(UserAccountService userAccountService,
                          PasswordEncoder passwordEncoder) {
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ REGISTER API (THIS WAS MISSING)
    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
        // passwordHash temporarily holds plain password
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return ResponseEntity.ok(userAccountService.register(user));
    }

    // ✅ LOGIN API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

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
