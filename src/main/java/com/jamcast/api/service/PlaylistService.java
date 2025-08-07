package com.jamcast.api.service;

import com.jamcast.api.dto.PlaylistRequest;
import com.jamcast.api.dto.PlaylistResponse;
import com.jamcast.api.dto.WeatherResponse;
import com.jamcast.api.dto.SpotifyRecommendationsResponse;
import com.jamcast.api.dto.PlaylistResponse.PlaylistTrack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Main service class that orchestrates the playlist generation process.
 * Coordinates between WeatherService and SpotifyService to create weather-based playlists.
 */
@Service
public class PlaylistService {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistService.class);

    private final WeatherService weatherService;
    private final SpotifyService spotifyService;

    @Autowired
    public PlaylistService(WeatherService weatherService, SpotifyService spotifyService) {
        this.weatherService = weatherService;
        this.spotifyService = spotifyService;
    }

    /**
     * Generates a playlist based on the weather conditions in a given city.
     * This is the main method that orchestrates the entire process.
     * 
     * @param request The playlist request containing the city name
     * @return Mono<PlaylistResponse> containing the generated playlist
     */
    public Mono<PlaylistResponse> generatePlaylist(PlaylistRequest request) {
        String city = request.getCity();
        logger.info("Starting playlist generation for city: {}", city);

        return weatherService.getWeatherForCity(city)
                .flatMap(weatherResponse -> {
                    logger.info("Weather retrieved for {}: {}", city, 
                            weatherResponse.getWeather().get(0).getMain());
                    
                    String mood = weatherService.mapWeatherToMood(weatherResponse);
                    String playlistTitle = weatherService.generatePlaylistTitle(weatherResponse, city);
                    String weatherCondition = weatherResponse.getWeather().get(0).getMain();
                    
                    logger.info("Mapped weather to mood: {} for playlist: {}", mood, playlistTitle);
                    
                    return spotifyService.getRecommendationsByMood(mood)
                            .map(spotifyResponse -> {
                                List<PlaylistTrack> tracks = spotifyService.convertToPlaylistTracks(
                                        spotifyResponse.getTracks());
                                
                                logger.info("Generated playlist with {} tracks for {} ({} mood)", 
                                        tracks.size(), city, mood);
                                
                                return new PlaylistResponse(
                                        playlistTitle,
                                        weatherCondition,
                                        mood,
                                        tracks,
                                        city
                                );
                            });
                })
                .doOnError(error -> logger.error("Error generating playlist for {}: {}", 
                        city, error.getMessage()));
    }
} 