package com.jamcast.api.dto;

import java.util.List;

/**
 * DTO for the final playlist response.
 * Contains the generated playlist title and list of tracks.
 */
public class PlaylistResponse {

    private String playlistTitle;
    private String weatherCondition;
    private String mood;
    private List<PlaylistTrack> tracks;
    private String city;

    // Default constructor for JSON serialization
    public PlaylistResponse() {}

    public PlaylistResponse(String playlistTitle, String weatherCondition, String mood, 
                          List<PlaylistTrack> tracks, String city) {
        this.playlistTitle = playlistTitle;
        this.weatherCondition = weatherCondition;
        this.mood = mood;
        this.tracks = tracks;
        this.city = city;
    }

    // Getters and Setters
    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public List<PlaylistTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<PlaylistTrack> tracks) {
        this.tracks = tracks;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Inner class representing a track in the playlist response
     */
    public static class PlaylistTrack {
        private String name;
        private String artist;
        private String spotifyUrl;
        private String albumName;

        // Default constructor
        public PlaylistTrack() {}

        public PlaylistTrack(String name, String artist, String spotifyUrl, String albumName) {
            this.name = name;
            this.artist = artist;
            this.spotifyUrl = spotifyUrl;
            this.albumName = albumName;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getSpotifyUrl() {
            return spotifyUrl;
        }

        public void setSpotifyUrl(String spotifyUrl) {
            this.spotifyUrl = spotifyUrl;
        }

        public String getAlbumName() {
            return albumName;
        }

        public void setAlbumName(String albumName) {
            this.albumName = albumName;
        }
    }
} 