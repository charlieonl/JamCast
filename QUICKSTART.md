# JamCast Quick Start Guide

## 🚀 Quick Setup

### Prerequisites Check
```bash
# Check Java version (need 17+)
java -version

# Check Maven version (need 3.6+)
mvn -version
```

### 1. Configure API Keys
Edit `src/main/resources/application.yml`:
```yaml
api:
  openweathermap:
    api-key: YOUR_OPENWEATHERMAP_API_KEY
  spotify:
    access-token: YOUR_SPOTIFY_ACCESS_TOKEN
```

### 2. Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### 3. Test the API
```bash
# Health check
curl http://localhost:8080/api/v1/health

# Generate playlist for Chicago
curl -X POST http://localhost:8080/api/v1/playlist \
     -H "Content-Type: application/json" \
     -d '{"city": "Chicago"}'
```

## 🎯 API Examples

### Request
```json
{
    "city": "Chicago"
}
```

### Response
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

## 🔧 Troubleshooting

### Common Issues

1. **Java not found**
   - Install Java 17+ from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)

2. **Maven not found**
   - Install Maven: `brew install maven` (macOS) or download from [Maven website](https://maven.apache.org/download.cgi)

3. **API key errors**
   - Get OpenWeatherMap API key: https://openweathermap.org/api
   - Get Spotify access token: https://developer.spotify.com/dashboard

4. **Port 8080 in use**
   - Change port in `application.yml`:
   ```yaml
   server:
     port: 8081
   ```

### Testing Without Real API Keys

For testing purposes, you can modify the services to return mock data:

1. Comment out the real API calls in `WeatherService.java` and `SpotifyService.java`
2. Return mock responses for testing
3. Uncomment when ready to use real APIs

## 📝 Development Notes

- The application uses Spring WebFlux for reactive programming
- All external API calls are non-blocking
- Comprehensive error handling is included
- Detailed logging for debugging
- Input validation on all endpoints

## 🎵 Weather to Mood Mapping

| Weather | Mood | Genres |
|---------|------|--------|
| Rain | Chill | chill, ambient, indie |
| Clear | Happy | pop, indie-pop, summer |
| Snow | Calm | ambient, classical |
| Clouds | Happy/Chill | Based on temperature |

## 📚 Next Steps

1. Read the full [README.md](README.md) for detailed documentation
2. Set up your API keys
3. Test with different cities
4. Customize the mood mapping logic
5. Add more sophisticated playlist generation features 