package interactive.tour.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TourController {
    @FXML
    private Label tourDescription;
    
    @FXML
    private Button nextButton;
    
    private int currentStop = 0;
    private final String[] tourStops = {
        "Welcome to our interactive tour! Let's begin our journey.",
        "This is the first stop of our tour. Here you can learn about the history.",
        "Moving on to the second stop, where you'll discover local culture.",
        "The final stop of our tour. Thank you for joining us!"
    };
    
    @FXML
    public void initialize() {
        updateTourContent();
    }
    
    @FXML
    private void nextStop() {
        currentStop++;
        if (currentStop >= tourStops.length) {
            currentStop = 0;
        }
        updateTourContent();
    }
    
    private void updateTourContent() {
        tourDescription.setText(tourStops[currentStop]);
        nextButton.setText(currentStop == tourStops.length - 1 ? "Restart Tour" : "Next");
    }
    
    @FXML
    private void backToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            
            Stage stage = (Stage) tourDescription.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void viewMap() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MapView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            
            Stage stage = (Stage) tourDescription.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 