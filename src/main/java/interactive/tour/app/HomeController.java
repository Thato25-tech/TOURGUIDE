package interactive.tour.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML private MediaView mediaView;
    @FXML private Button btnOpenMap;
    
    private MediaPlayer mediaPlayer;
    
    @FXML
    public void initialize() {
        // Set up map button handler
        btnOpenMap.setOnAction(event -> openMap());
        
        // Try to play welcome video
        try {
            String videoPath = getClass().getResource("/media/lesotho_intro.mp4").toExternalForm();
            Media media = new Media(videoPath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaView.setVisible(true);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error loading welcome video: " + e.getMessage());
            mediaView.setVisible(false);
        }
    }
    
    @FXML
    private void openMap() {
        try {
            // Stop current media if playing
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interactive/tour/app/MapView.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnOpenMap.getScene().getWindow();
            stage.setTitle("Lesotho Tour Guide - Interactive Map");
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println("Error opening map: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 