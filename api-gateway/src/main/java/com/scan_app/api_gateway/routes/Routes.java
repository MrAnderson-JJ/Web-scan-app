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
}
