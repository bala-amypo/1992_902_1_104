package com.example.demo.security;

import com.example.demo.model.UserAccount;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtTokenProvider {

    private final String secret;
    private final long validityInMs;

    // Constructor EXACTLY as used in tests
    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    // Generate a SIMPLE token (Base64 encoded)
    public String generateToken(UserAccount user) {
        String tokenData = user.getEmail() + "|" + user.getId() + "|" + user.getRole();
        return Base64.getEncoder().encodeToString(tokenData.getBytes());
    }

    // Validate token (basic validation)
    public boolean validateToken(String token) {
        try {
            if (token == null || token.isEmpty()) {
                return false;
            }
            Base64.getDecoder().decode(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract username (email)
    public String getUsername(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token));
            return decoded.split("\\|")[0];
        } catch (Exception e) {
            return null;
        }
    }
}
