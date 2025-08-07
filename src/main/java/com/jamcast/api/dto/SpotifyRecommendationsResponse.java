package com.jamcast.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for Spotify recommendations API response.
 * Maps the JSON response from Spotify recommendations endpoint.
 */
public class SpotifyRecommendationsResponse {

    @JsonProperty("tracks")
    private SpotifyTrack[] tracks;

    @JsonProperty("seeds")
    private SpotifySeed[] seeds;

    // Default constructor for JSON deserialization
    public SpotifyRecommendationsResponse() {}

    // Getters and Setters
    public SpotifyTrack[] getTracks() {
        return tracks;
    }

    public void setTracks(SpotifyTrack[] tracks) {
        this.tracks = tracks;
    }

    public SpotifySeed[] getSeeds() {
        return seeds;
    }

    public void setSeeds(SpotifySeed[] seeds) {
        this.seeds = seeds;
    }

    /**
     * Inner class for Spotify seed information
     */
    public static class SpotifySeed {
        @JsonProperty("afterFilteringSize")
        private int afterFilteringSize;

        @JsonProperty("afterRelinkingSize")
        private int afterRelinkingSize;

        @JsonProperty("href")
        private String href;

        @JsonProperty("id")
        private String id;

        @JsonProperty("initialPoolSize")
        private int initialPoolSize;

        @JsonProperty("type")
        private String type;

        // Default constructor
        public SpotifySeed() {}

        // Getters and Setters
        public int getAfterFilteringSize() {
            return afterFilteringSize;
        }

        public void setAfterFilteringSize(int afterFilteringSize) {
            this.afterFilteringSize = afterFilteringSize;
        }

        public int getAfterRelinkingSize() {
            return afterRelinkingSize;
        }

        public void setAfterRelinkingSize(int afterRelinkingSize) {
            this.afterRelinkingSize = afterRelinkingSize;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getInitialPoolSize() {
            return initialPoolSize;
        }

        public void setInitialPoolSize(int initialPoolSize) {
            this.initialPoolSize = initialPoolSize;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
} 