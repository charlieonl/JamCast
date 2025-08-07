package com.jamcast.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for API endpoints and keys.
 * Maps properties from application.yml to Java objects.
 */
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiConfig {

    private OpenWeatherMap openweathermap;
    private Spotify spotify;

    // Default constructor
    public ApiConfig() {}

    // Getters and Setters
    public OpenWeatherMap getOpenweathermap() {
        return openweathermap;
    }

    public void setOpenweathermap(OpenWeatherMap openweathermap) {
        this.openweathermap = openweathermap;
    }

    public Spotify getSpotify() {
        return spotify;
    }

    public void setSpotify(Spotify spotify) {
        this.spotify = spotify;
    }

    /**
     * Inner class for OpenWeatherMap configuration
     */
    public static class OpenWeatherMap {
        private String baseUrl;
        private String apiKey;

        // Default constructor
        public OpenWeatherMap() {}

        // Getters and Setters
        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }
    }

    /**
     * Inner class for Spotify configuration
     */
    public static class Spotify {
        private String baseUrl;
        private String accessToken;

        // Default constructor
        public Spotify() {}

        // Getters and Setters
        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
} 