package com.jamcast.api.controller;

import com.jamcast.api.dto.PlaylistRequest;
import com.jamcast.api.dto.PlaylistResponse;
import com.jamcast.api.service.PlaylistService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller for playlist-related endpoints.
 * Handles HTTP requests for weather-based playlist generation.
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*") // Allow CORS for development
public class PlaylistController {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    /**
     * POST endpoint for generating weather-based playlists.
     * 
     * @param request The playlist request containing the city name
     * @return ResponseEntity containing the generated playlist
     */
    @PostMapping("/playlist")
    public Mono<ResponseEntity<PlaylistResponse>> generatePlaylist(@Valid @RequestBody PlaylistRequest request) {
        logger.info("Received playlist request for city: {}", request.getCity());
        
        return playlistService.generatePlaylist(request)
                .map(playlist -> {
                    logger.info("Successfully generated playlist for {} with {} tracks", 
                            request.getCity(), playlist.getTracks().size());
                    return ResponseEntity.ok(playlist);
                })
                .onErrorResume(error -> {
                    logger.error("Error processing playlist request for {}: {}", 
                            request.getCity(), error.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new PlaylistResponse()));
                });
    }

    /**
     * GET endpoint for health check.
     * 
     * @return Simple health status
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("JamCast API is running!");
    }

    /**
     * GET endpoint for API information.
     * 
     * @return API information
     */
    @GetMapping("/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("""
                JamCast API - Weather-based Spotify Playlist Generator
                
                Endpoints:
                - POST /api/v1/playlist - Generate playlist based on city weather
                - GET /api/v1/health - Health check
                - GET /api/v1/info - This information
                
                Example request:
                {
                    "city": "Chicago"
                }
                """);
    }
} 