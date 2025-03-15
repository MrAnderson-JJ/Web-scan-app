package com.scan_app.api_gateway.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import java.io.IOException;
import java.util.*;

public class JwtHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            String userId = jwtAuth.getName();

            MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
            mutableRequest.putHeader("X-User-ID", userId);
            mutableRequest.removedHeaders.add("Authorization");

            // logování pro kontrolu všech hlaviček:
            System.out.println(" Headers posílané z API Gateway:");
            Collections.list(mutableRequest.getHeaderNames())
                    .forEach(headerName -> System.out.println(headerName + ": " + mutableRequest.getHeader(headerName)));

            filterChain.doFilter(mutableRequest, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    static class MutableHttpServletRequest extends HttpServletRequestWrapper {
        private final Map<String, String> customHeaders = new HashMap<>();
        private final Set<String> removedHeaders = new HashSet<>();

        MutableHttpServletRequest(HttpServletRequest request) {
            super(request);
        }

        public void putHeader(String name, String value) {
            customHeaders.put(name, value);
        }

        @Override
        public String getHeader(String name) {
            if (customHeaders.containsKey(name)) {
                return customHeaders.get(name);
            }
            if ("Authorization".equalsIgnoreCase(name)) {
                return null;  // zajišťuje odstranění hlavičky
            }
            return super.getHeader(name);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            if (customHeaders.containsKey(name)) {
                return Collections.enumeration(List.of(customHeaders.get(name)));
            }
            if ("Authorization".equalsIgnoreCase(name)) {
                return Collections.emptyEnumeration();
            }
            return super.getHeaders(name);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            Set<String> headerNames = new HashSet<>(Collections.list(super.getHeaderNames()));
            headerNames.addAll(customHeaders.keySet());
            headerNames.remove("Authorization"); // explicitně odstraněno
            return Collections.enumeration(headerNames);
        }
    }
}


