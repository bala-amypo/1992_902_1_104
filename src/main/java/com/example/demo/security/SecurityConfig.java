package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF (required for APIs)
            .csrf(csrf -> csrf.disable())

            // Allow ALL requests without authentication
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())

            // Disable Spring Security login page
            .formLogin(form -> form.disable())

            // Disable basic auth
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
