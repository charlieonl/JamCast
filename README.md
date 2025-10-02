# JamCast - Weather-based Spotify Playlist Generator

JamCast is a Spring Boot REST API that generates Spotify playlists based on the current weather conditions in a given city. The application combines weather data from OpenWeatherMap API with music recommendations from Spotify API to create personalized playlists.

## Features

-  **Weather Integration**: Fetches real-time weather data from OpenWeatherMap API
-  **Spotify Integration**: Generates music recommendations using Spotify Web API
-  **Smart Mood Mapping**: Maps weather conditions to appropriate music moods
-  **Reactive Programming**: Built with Spring WebFlux for non-blocking operations
-  **Comprehensive Logging**: Detailed logging for debugging and monitoring
-  **Error Handling**: Robust error handling with meaningful error messages
-  **Input Validation**: Request validation with detailed error responses

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- OpenWeatherMap API key
- Spotify API access token

## Setup

### 1. Clone and Navigate to Project
```bash
cd JamCast
```

### 2. Configure API Keys
Edit `src/main/resources/application.yml` and replace the placeholder values:

```yaml
api:
  openweathermap:
    api-key: YOUR_OPENWEATHERMAP_API_KEY  # Get from https://openweathermap.org/api
  spotify:
    access-token: YOUR_SPOTIFY_ACCESS_TOKEN  # Get from Spotify Developer Dashboard
```

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### POST /api/v1/playlist
Generates a weather-based playlist for a given city.

**Request Body:**
```json
{
    "city": "Chicago"
}
```

**Response:**
```json
{
    "playlistTitle": "Happy Vibes in Chicago",
    "weatherCondition": "Clear",
    "mood": "happy",
    "city": "Chicago",
    "tracks": [
        {
            "name": "Song Name",
            "artist": "Artist Name",
            "spotifyUrl": "https://open.spotify.com/track/...",
            "albumName": "Album Name"
        }
    ]
}
```

### GET /api/v1/health
Health check endpoint.

**Response:**
```
JamCast API is running!
```

### GET /api/v1/info
API information and usage instructions.

## Weather to Mood Mapping

The application maps weather conditions to music moods:

| Weather Condition | Mood | Spotify Genres |
|------------------|------|----------------|
| Rain/Drizzle/Thunderstorm | Chill | chill, ambient, indie |
| Clear | Happy | pop, indie-pop, summer |
| Snow | Calm | ambient, classical, instrumental |
| Clouds (warm) | Happy | pop, indie-pop, summer |
| Clouds (cold) | Chill | chill, ambient, indie |
| Mist/Fog/Haze | Calm | ambient, classical, instrumental |
| Smoke/Dust/Sand/Ash | Chill | chill, ambient, indie |
| Squall/Tornado | Energetic | rock, electronic, dance |

## Project Structure

```
JamCast/
├── src/main/java/com/jamcast/api/
│   ├── JamCastApplication.java          # Main application class
│   ├── controller/
│   │   └── PlaylistController.java      # REST endpoints
│   ├── service/
│   │   ├── PlaylistService.java         # Main orchestration service
│   │   ├── WeatherService.java          # Weather API integration
│   │   └── SpotifyService.java          # Spotify API integration
│   ├── dto/
│   │   ├── PlaylistRequest.java         # Request DTO
│   │   ├── PlaylistResponse.java        # Response DTO
│   │   ├── WeatherResponse.java         # Weather API response
│   │   ├── SpotifyTrack.java            # Spotify track DTO
│   │   └── SpotifyRecommendationsResponse.java # Spotify recommendations
│   ├── config/
│   │   └── ApiConfig.java               # Configuration properties
│   └── exception/
│       └── GlobalExceptionHandler.java   # Error handling
├── src/main/resources/
│   └── application.yml                  # Application configuration
├── pom.xml                             # Maven dependencies
└── README.md                           # This file
```

## Getting API Keys

### OpenWeatherMap API
1. Go to [OpenWeatherMap](https://openweathermap.org/)
2. Sign up for a free account
3. Navigate to "API keys" section
4. Copy your API key

### Spotify API
1. Go to [Spotify Developer Dashboard](https://developer.spotify.com/dashboard/)
2. Create a new application
3. Get your Client ID and Client Secret
4. Use a tool like Postman or curl to get an access token:

```bash
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" \
     -d "grant_type=client_credentials&client_id=YOUR_CLIENT_ID&client_secret=YOUR_CLIENT_SECRET" \
     https://accounts.spotify.com/api/token
```

## Testing the API

### Using curl
```bash
curl -X POST http://localhost:8080/api/v1/playlist \
     -H "Content-Type: application/json" \
     -d '{"city": "Chicago"}'
```

### Using Postman
1. Create a new POST request
2. Set URL to `http://localhost:8080/api/v1/playlist`
3. Set Content-Type header to `application/json`
4. Set request body to:
```json
{
    "city": "Chicago"
}
```

## Error Handling

The API provides detailed error responses for various scenarios:

- **Validation Errors**: Invalid request format or missing required fields
- **API Errors**: External service failures (OpenWeatherMap/Spotify)
- **Server Errors**: Internal application errors

All errors include:
- Error type
- Descriptive message
- HTTP status code
- Additional details when available

## Logging

The application uses SLF4J with detailed logging:
- DEBUG level for service operations
- INFO level for web requests
- ERROR level for exceptions

Logs include:
- API request/response details
- Weather data retrieval
- Spotify recommendations
- Error stack traces

## Future Enhancements

- [ ] Spotify OAuth integration for user-specific playlists
- [ ] Caching for weather and music data
- [ ] More sophisticated mood mapping algorithms
- [ ] Playlist history and favorites
- [ ] Multiple weather data sources
- [ ] Advanced music filtering options

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License. 
