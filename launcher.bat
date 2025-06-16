@echo off
echo Starting Lesotho Tour Guide...

:: Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 17 or later from https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

:: Set JavaFX module path
set PATH_TO_FX="C:\Program Files\Java\javafx-sdk-17.0.2\lib"

:: Run the application with JavaFX modules
java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml,javafx.media -jar lesotho-tour-guide-1.0-SNAPSHOT.jar

if %errorlevel% neq 0 (
    echo Error: Application failed to start
    echo Please make sure all required files are present
    pause
    exit /b 1
)

exit /b 0 