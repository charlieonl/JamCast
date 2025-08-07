package com.jamcast.api.service;

import com.jamcast.api.config.ApiConfig;
import com.jamcast.api.dto.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Service class for handling weather-related operations.
 * Responsible for calling OpenWeatherMap API and processing weather data.
 */
@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final WebClient webClient;
    private final ApiConfig apiConfig;

    @Autowired
    public WeatherService(WebClient webClient, ApiConfig apiConfig) {
        this.webClient = webClient;
        this.apiConfig = apiConfig;
    }

    /**
     * Fetches current weather data for a given city.
     * 
     * @param city The city name to get weather for
     * @return WeatherResponse containing weather information
     */
    public Mono<WeatherResponse> getWeatherForCity(String city) {
        String url = buildWeatherUrl(city);
        logger.info("Fetching weather data for city: {}", city);
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .doOnSuccess(response -> logger.info("Successfully retrieved weather for {}: {}", 
                        city, response.getWeather().get(0).getMain()))
                .doOnError(error -> logger.error("Error fetching weather for {}: {}", city, error.getMessage()));
    }

    /**
     * Maps weather conditions to a general mood for playlist generation.
     * 
     * @param weatherResponse The weather response to analyze
     * @return A mood string suitable for Spotify recommendations
     */
    public String mapWeatherToMood(WeatherResponse weatherResponse) {
        if (weatherResponse.getWeather() == null || weatherResponse.getWeather().isEmpty()) {
            logger.warn("No weather data available, defaulting to 'happy' mood");
            return "happy";
        }

        String weatherMain = weatherResponse.getWeather().get(0).getMain();
        double temperature = weatherResponse.getMain().getTemp();
        
        logger.info("Mapping weather '{}' at {}°C to mood", weatherMain, temperature);

        return switch (weatherMain.toLowerCase()) {
            case "rain", "drizzle", "thunderstorm" -> "chill";
            case "clear" -> "happy";
            case "snow" -> "calm";
            case "clouds" -> temperature > 20 ? "happy" : "chill";
            case "mist", "fog", "haze" -> "calm";
            case "smoke", "dust", "sand", "ash" -> "chill";
            case "squall", "tornado" -> "energetic";
            default -> {
                // Fallback based on temperature
                if (temperature > 25) yield "happy";
                else if (temperature < 10) yield "chill";
                else yield "calm";
            }
        };
    }

    /**
     * Generates a playlist title based on weather conditions and city.
     * 
     * @param weatherResponse The weather response
     * @param city The city name
     * @return A descriptive playlist title
     */
    public String generatePlaylistTitle(WeatherResponse weatherResponse, String city) {
        String weatherCondition = weatherResponse.getWeather().get(0).getMain();
        String mood = mapWeatherToMood(weatherResponse);
        
        return String.format("%s Vibes in %s", 
                capitalizeFirstLetter(mood), 
                capitalizeFirstLetter(city));
    }

    /**
     * Builds the OpenWeatherMap API URL with parameters.
     * 
     * @param city The city name
     * @return Complete API URL
     */
    private String buildWeatherUrl(String city) {
        return String.format("%s?q=%s&appid=%s&units=metric",
                apiConfig.getOpenweathermap().getBaseUrl(),
                city,
                apiConfig.getOpenweathermap().getApiKey());
    }

    /**
     * Capitalizes the first letter of a string.
     * 
     * @param str The string to capitalize
     * @return The capitalized string
     */
    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
} 