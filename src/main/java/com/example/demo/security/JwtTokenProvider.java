// package com.example.demo.security;

// import com.example.demo.model.UserAccount;
// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Date;

// @Component
// public class JwtTokenProvider {

//     private final Key key;
//     private final long validityMs;

//     public JwtTokenProvider(
//             @Value("${app.jwt.secret}") String secret,
//             @Value("${app.jwt.expiration-ms}") long validityMs) {
//         this.key = Keys.hmacShaKeyFor(secret.getBytes());
//         this.validityMs = validityMs;
//     }

//     public String generateToken(UserAccount user) {
//         return Jwts.builder()
//                 .setSubject(user.getEmail())
//                 .claim("role", user.getRole())
//                 .claim("userId", user.getId())
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + validityMs))
//                 .signWith(key, SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//             return true;
//         } catch (JwtException | IllegalArgumentException ex) {
//             return false;
//         }
//     }

//     public String getEmail(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//     }
// }

