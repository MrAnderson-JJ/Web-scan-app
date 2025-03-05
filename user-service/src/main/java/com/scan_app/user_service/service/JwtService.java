package com.scan_app.user_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.util.Base64URL;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class JwtService {

    private final HttpServletRequest request;
    private final ObjectMapper objectMapper;

    public JwtService(HttpServletRequest request, ObjectMapper objectMapper) {
        this.request = request;
        this.objectMapper = objectMapper;
    }

    // Extract the raw JWT token from the Authorization header
    public String getRawToken() {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);  // Remove "Bearer " prefix
        }
        return null;  // No token found
    }

    // Decode JWT and extract the user ID (sub claim)
    public String getUserIdFromToken() {
        String token = getRawToken();
        if (token == null) {
            return null;
        }

        try {
            String[] parts = token.split("\\.");
            if (parts.length < 2) {
                return null;  // Invalid token format
            }

            String payloadJson = new String(Base64URL.from(parts[1]).decode());
            Map<String, Object> payloadMap = objectMapper.readValue(payloadJson, Map.class);

            return (String) payloadMap.get("sub");  // Extract 'sub' claim (user ID)
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
