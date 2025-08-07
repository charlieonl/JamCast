package com.jamcast.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Main Spring Boot application class for JamCast API.
 * This application provides weather-based Spotify playlist recommendations.
 */
@SpringBootApplication
public class JamCastApplication {

    public static void main(String[] args) {
        SpringApplication.run(JamCastApplication.class, args);
    }

    /**
     * Creates a WebClient bean for making HTTP requests to external APIs.
     * Used for calling OpenWeatherMap and Spotify APIs.
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
} 