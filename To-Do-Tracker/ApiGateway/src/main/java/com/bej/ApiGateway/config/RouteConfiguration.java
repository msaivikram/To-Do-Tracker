package com.bej.ApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){

        return  builder.routes()
                .route((p)->p
                        .path("/api/v1/**")
//                        .uri("http://localhost:8083/"))
                        .uri("lb://user-authentication-service"))

                .route((p)->p
                        .path("/api/v2/**")
//                        .uri("http://localhost:8087/"))
                        .uri("lb://user-task-service"))

                .route((p)->p
                        .path("/api/v3/**")
//                        .uri("http://localhost:8090/"))
                        .uri("lb://notification-service"))
                .build();

    }
}
