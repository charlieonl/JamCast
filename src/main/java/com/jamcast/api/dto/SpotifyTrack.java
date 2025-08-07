package com.jamcast.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO representing a Spotify track.
 * Maps individual track data from Spotify API responses.
 */
public class SpotifyTrack {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    @JsonProperty("artists")
    private SpotifyArtist[] artists;

    @JsonProperty("album")
    private SpotifyAlbum album;

    // Default constructor for JSON deserialization
    public SpotifyTrack() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public void setExternalUrls(ExternalUrls externalUrls) {
        this.externalUrls = externalUrls;
    }

    public SpotifyArtist[] getArtists() {
        return artists;
    }

    public void setArtists(SpotifyArtist[] artists) {
        this.artists = artists;
    }

    public SpotifyAlbum getAlbum() {
        return album;
    }

    public void setAlbum(SpotifyAlbum album) {
        this.album = album;
    }

    /**
     * Inner class for external URLs
     */
    public static class ExternalUrls {
        @JsonProperty("spotify")
        private String spotify;

        // Default constructor
        public ExternalUrls() {}

        public String getSpotify() {
            return spotify;
        }

        public void setSpotify(String spotify) {
            this.spotify = spotify;
        }
    }

    /**
     * Inner class for Spotify artist
     */
    public static class SpotifyArtist {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        // Default constructor
        public SpotifyArtist() {}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * Inner class for Spotify album
     */
    public static class SpotifyAlbum {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("images")
        private SpotifyImage[] images;

        // Default constructor
        public SpotifyAlbum() {}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public SpotifyImage[] getImages() {
            return images;
        }

        public void setImages(SpotifyImage[] images) {
            this.images = images;
        }
    }

    /**
     * Inner class for Spotify image
     */
    public static class SpotifyImage {
        @JsonProperty("url")
        private String url;

        @JsonProperty("height")
        private int height;

        @JsonProperty("width")
        private int width;

        // Default constructor
        public SpotifyImage() {}

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
} 