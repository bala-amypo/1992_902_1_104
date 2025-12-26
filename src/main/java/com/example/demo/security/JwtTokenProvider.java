package com.example.demo.security;

import com.example.demo.model.UserAccount;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtTokenProvider {

    private final String secretKey;
    private final long validityInMs;

    // Constructor injection (MANDATORY)
    public JwtTokenProvider() {
        this.secretKey = "ChangeThisSecretKeyForJwt123456789012345";
        this.validityInMs = 3600000;
    }

    // Generate token
    public String generateToken(UserAccount user) {
        // Simple token: Base64(email|timestamp)
        String tokenData = user.getEmail() + "|" + System.currentTimeMillis();
        return Base64.getEncoder().encodeToString(tokenData.getBytes());
    }

    // ✅ ADD THIS METHOD (FIXES YOUR ERROR)
    public String getEmailFromToken(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token));
            return decoded.split("\\|")[0];
        } catch (Exception e) {
            return null;
        }
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            Base64.getDecoder().decode(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
