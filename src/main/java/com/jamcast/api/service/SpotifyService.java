package com.jamcast.api.service;

import com.jamcast.api.config.ApiConfig;
import com.jamcast.api.dto.SpotifyRecommendationsResponse;
import com.jamcast.api.dto.SpotifyTrack;
import com.jamcast.api.dto.PlaylistResponse.PlaylistTrack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling Spotify-related operations.
 * Responsible for calling Spotify API and processing track recommendations.
 */
@Service
public class SpotifyService {

    private static final Logger logger = LoggerFactory.getLogger(SpotifyService.class);

    private final WebClient webClient;
    private final ApiConfig apiConfig;

    @Autowired
    public SpotifyService(WebClient webClient, ApiConfig apiConfig) {
        this.webClient = webClient;
        this.apiConfig = apiConfig;
    }

    /**
     * Gets track recommendations from Spotify based on mood.
     * 
     * @param mood The mood to base recommendations on
     * @return SpotifyRecommendationsResponse containing recommended tracks
     */
    public Mono<SpotifyRecommendationsResponse> getRecommendationsByMood(String mood) {
        String url = buildRecommendationsUrl(mood);
        logger.info("Fetching Spotify recommendations for mood: {}", mood);
        
        return webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + apiConfig.getSpotify().getAccessToken())
                .retrieve()
                .bodyToMono(SpotifyRecommendationsResponse.class)
                .doOnSuccess(response -> logger.info("Successfully retrieved {} tracks for mood: {}", 
                        response.getTracks().length, mood))
                .doOnError(error -> logger.error("Error fetching Spotify recommendations for mood {}: {}", 
                        mood, error.getMessage()));
    }

    /**
     * Converts Spotify tracks to PlaylistTrack objects for the response.
     * 
     * @param spotifyTracks Array of Spotify tracks
     * @return List of PlaylistTrack objects
     */
    public List<PlaylistTrack> convertToPlaylistTracks(SpotifyTrack[] spotifyTracks) {
        return Arrays.stream(spotifyTracks)
                .map(this::convertToPlaylistTrack)
                .collect(Collectors.toList());
    }

    /**
     * Converts a single Spotify track to a PlaylistTrack.
     * 
     * @param spotifyTrack The Spotify track to convert
     * @return PlaylistTrack object
     */
    private PlaylistTrack convertToPlaylistTrack(SpotifyTrack spotifyTrack) {
        String artistName = spotifyTrack.getArtists() != null && spotifyTrack.getArtists().length > 0 
                ? spotifyTrack.getArtists()[0].getName() 
                : "Unknown Artist";
        
        String albumName = spotifyTrack.getAlbum() != null 
                ? spotifyTrack.getAlbum().getName() 
                : "Unknown Album";
        
        String spotifyUrl = spotifyTrack.getExternalUrls() != null 
                ? spotifyTrack.getExternalUrls().getSpotify() 
                : "";

        return new PlaylistTrack(
                spotifyTrack.getName(),
                artistName,
                spotifyUrl,
                albumName
        );
    }

    /**
     * Maps mood to Spotify seed genres for recommendations.
     * 
     * @param mood The mood to map
     * @return A comma-separated string of seed genres
     */
    private String mapMoodToSeedGenres(String mood) {
        return switch (mood.toLowerCase()) {
            case "happy" -> "pop,indie-pop,summer";
            case "chill" -> "chill,ambient,indie";
            case "calm" -> "ambient,classical,instrumental";
            case "energetic" -> "rock,electronic,dance";
            default -> "pop,indie";
        };
    }

    /**
     * Builds the Spotify recommendations API URL with parameters.
     * 
     * @param mood The mood for recommendations
     * @return Complete API URL
     */
    private String buildRecommendationsUrl(String mood) {
        String seedGenres = mapMoodToSeedGenres(mood);
        
        return String.format("%s/recommendations?seed_genres=%s&limit=20&target_valence=0.7",
                apiConfig.getSpotify().getBaseUrl(),
                seedGenres);
    }
} 