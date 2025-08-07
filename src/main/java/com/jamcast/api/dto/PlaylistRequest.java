package com.jamcast.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for playlist request containing the city name.
 * Used as the request body for the /playlist endpoint.
 */
public class PlaylistRequest {

    @NotBlank(message = "City name is required")
    @Size(min = 1, max = 100, message = "City name must be between 1 and 100 characters")
    private String city;

    // Default constructor for JSON deserialization
    public PlaylistRequest() {}

    public PlaylistRequest(String city) {
        this.city = city;
    }

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "PlaylistRequest{" +
                "city='" + city + '\'' +
                '}';
    }
} 