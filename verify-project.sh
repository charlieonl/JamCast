#!/bin/bash

echo "🎵 JamCast Project Verification"
echo "================================"

# Check if all required files exist
echo "Checking project structure..."

required_files=(
    "pom.xml"
    "src/main/java/com/jamcast/api/JamCastApplication.java"
    "src/main/java/com/jamcast/api/controller/PlaylistController.java"
    "src/main/java/com/jamcast/api/service/PlaylistService.java"
    "src/main/java/com/jamcast/api/service/WeatherService.java"
    "src/main/java/com/jamcast/api/service/SpotifyService.java"
    "src/main/java/com/jamcast/api/dto/PlaylistRequest.java"
    "src/main/java/com/jamcast/api/dto/PlaylistResponse.java"
    "src/main/java/com/jamcast/api/dto/WeatherResponse.java"
    "src/main/java/com/jamcast/api/dto/SpotifyTrack.java"
    "src/main/java/com/jamcast/api/dto/SpotifyRecommendationsResponse.java"
    "src/main/java/com/jamcast/api/config/ApiConfig.java"
    "src/main/java/com/jamcast/api/exception/GlobalExceptionHandler.java"
    "src/main/resources/application.yml"
    "README.md"
)

all_files_exist=true

for file in "${required_files[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ $file (missing)"
        all_files_exist=false
    fi
done

echo ""
echo "Project Structure Summary:"
echo "=========================="

if [ "$all_files_exist" = true ]; then
    echo "✅ All required files are present!"
    echo ""
    echo "📁 Project Structure:"
    echo "├── pom.xml (Maven configuration)"
    echo "├── README.md (Documentation)"
    echo "└── src/main/java/com/jamcast/api/"
    echo "    ├── JamCastApplication.java (Main application)"
    echo "    ├── controller/"
    echo "    │   └── PlaylistController.java (REST endpoints)"
    echo "    ├── service/"
    echo "    │   ├── PlaylistService.java (Orchestration)"
    echo "    │   ├── WeatherService.java (Weather API)"
    echo "    │   └── SpotifyService.java (Spotify API)"
    echo "    ├── dto/ (Data Transfer Objects)"
    echo "    ├── config/"
    echo "    │   └── ApiConfig.java (Configuration)"
    echo "    └── exception/"
    echo "        └── GlobalExceptionHandler.java (Error handling)"
    echo ""
    echo "🚀 Next Steps:"
    echo "1. Install Java 17 or higher"
    echo "2. Install Maven 3.6 or higher"
    echo "3. Configure API keys in src/main/resources/application.yml"
    echo "4. Run: mvn spring-boot:run"
    echo ""
    echo "📖 For detailed setup instructions, see README.md"
else
    echo "❌ Some required files are missing. Please check the project structure."
fi

echo ""
echo "🎯 API Endpoints:"
echo "- POST /api/v1/playlist - Generate weather-based playlist"
echo "- GET /api/v1/health - Health check"
echo "- GET /api/v1/info - API information" 