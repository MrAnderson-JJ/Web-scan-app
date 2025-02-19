package com.scan_app.output_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())  // Allow ALL requests (NO AUTH)
                .csrf(csrf -> csrf.disable()) // Completely disable CSRF protection
                .cors(cors -> cors.disable()) // Completely disable CORS restrictions
                .formLogin(login -> login.disable()) // Disable login form
                .httpBasic(basic -> basic.disable()); // Disable basic authentication

        return http.build();
    }
}
