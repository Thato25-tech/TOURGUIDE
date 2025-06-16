# Lesotho Interactive Tour Guide

An immersive JavaFX application for virtual tours of Lesotho's cities, landmarks, and malls with audio guides, videos, and interactive quizzes.

## Features

- **Virtual Tours**: Explore five key locations in Lesotho:
  - Thaba Bosiu (Mountain Fortress)
  - Sehlabathebe National Park
  - Maseru City (Capital)
  - Katse Dam
  - Pioneer Shopping Mall

- **Multimedia Experience**:
  - High-quality videos of each location
  - Audio guides with informative narration
  - Detailed descriptions of each location

- **Interactive Learning**:
  - Knowledge quizzes for each location
  - Score tracking and feedback
  - Educational content about Lesotho's history and culture

## Prerequisites

- Java 11 or later
- JavaFX SDK 17 or later
- Maven or Gradle (for dependency management)

## Project Structure

```
src/
├── interactive/tour/app/      # Application source files
│   ├── Main.java              # Application entry point
│   ├── Home.fxml              # Home screen UI
│   ├── HomeController.java    # Home screen controller
│   ├── Location.fxml          # Location details UI
│   ├── LocationController.java # Location controller
│   ├── Quiz.fxml              # Quiz UI
│   └── QuizController.java    # Quiz controller
├── styles/                    # CSS styling
│   └── app.css                # Application styles
└── resources/                 # Media resources
    └── media/                 # Audio and video files for the tour
        ├── thaba_bosiu.mp4
        ├── thaba_bosiu_audio.mp3
        ├── sehlabathebe.mp4
        └── ...
```

## Setup Instructions

1. **Clone the repository**:
   ```
   git clone [repository-url]
   cd lesotho-tour-guide
   ```

2. **Prepare media files**:
   - Create a `resources/media` directory in the project root
   - Add video files (.mp4) for each location with appropriate names:
     - `thaba_bosiu.mp4`
     - `sehlabathebe.mp4`
     - `maseru.mp4`
     - `katse_dam.mp4`
     - `pioneer_mall.mp4`
     - `lesotho_intro.mp4` (for the home screen)
   - Add audio narration files (.mp3) for each location:
     - `thaba_bosiu_audio.mp3`
     - `sehlabathebe_audio.mp3`
     - etc.

3. **Running the application**:
   ```
   javac -cp .:path/to/javafx-sdk/lib --module-path path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml,javafx.media src/interactive/tour/app/Main.java
   java -cp .:path/to/javafx-sdk/lib --module-path path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml,javafx.media interactive.tour.app.Main
   ```

   Or if using an IDE (Eclipse, IntelliJ IDEA, etc.), configure the JavaFX SDK and run the Main class.

## Using the Application

1. **Home Screen**:
   - Watch the introductory video about Lesotho
   - Select a location to explore from the available buttons

2. **Location Screen**:
   - View videos of the selected location
   - Listen to audio narration by clicking "Play Audio Guide"
   - Read detailed information about the location
   - Control video playback with play, pause, and rewind buttons
   - Test your knowledge by clicking "Take Quiz"

3. **Quiz Screen**:
   - Answer location-specific questions
   - Receive instant feedback on your answers
   - View your score at the end of the quiz
   - Return to the location screen after completing the quiz

## Extending the Application

To add new locations:

1. Add location data in `LocationController.java` LOCATIONS map
2. Add quiz questions in `QuizController.java` LOCATION_QUESTIONS map
3. Add corresponding media files in the resources directory
4. Update the Home.fxml to include a button for the new location

## License

[Include your license information here]

## Acknowledgments

- Images and videos courtesy of [source attribution]
- Audio narration by [source attribution]
- Historical and cultural information from [source attribution]