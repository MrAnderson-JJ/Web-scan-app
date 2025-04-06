package com.scan_app.api_gateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.server.ServerWebExchange;

@Configuration
public class JwtHeaderFilter {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter jwtHeaderGlobalFilter() {
        return (exchange, chain) -> ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> securityContext.getAuthentication())
                .filter(auth -> auth instanceof JwtAuthenticationToken)
                .cast(JwtAuthenticationToken.class)
                .flatMap(jwtAuth -> {
                    String userId = jwtAuth.getName();

                    // Modify request headers
                    ServerWebExchange mutatedExchange = exchange.mutate()
                            .request(builder -> builder.header("X-User-ID", userId)
                                    .headers(headers -> headers.remove(HttpHeaders.AUTHORIZATION)))
                            .build();

                    return chain.filter(mutatedExchange);
                })
                .switchIfEmpty(chain.filter(exchange));
    }
}

