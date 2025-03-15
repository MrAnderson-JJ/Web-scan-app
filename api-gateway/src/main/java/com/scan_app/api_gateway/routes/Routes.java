package com.scan_app.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.*;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> outputServiceRoute() {
        return GatewayRouterFunctions.route("output-service")
                .route(RequestPredicates.path("/api/output/**"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> scanServiceRoute() {
        return GatewayRouterFunctions.route("scan-service")
                .route(RequestPredicates.path("/api/scan/**"), HandlerFunctions.http("http://localhost:8080"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return GatewayRouterFunctions.route("user-service")
                .route(RequestPredicates.path("/api/user/**"), HandlerFunctions.http("http://localhost:8083"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> frontendRoute() {
        return GatewayRouterFunctions.route("frontend-service")
                .route(RequestPredicates.all(), HandlerFunctions.http("http://localhost:5173"))
                .build();
    }

}
