package interactive.tour.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MapController {
    @FXML private ImageView mapView;
    @FXML private Pane markerPane;
    @FXML private Button btnBack;
    
    @FXML
    public void initialize() {
        try {
            // Load the map image
            Image mapImage = new Image(getClass().getResourceAsStream("/map.png"));
            mapView.setImage(mapImage);
            
            // Set up back button action
            btnBack.setOnAction(event -> goBackToHome());
            
            // Add location markers
            addLocationMarkers();
        } catch (Exception e) {
            System.out.println("Error initializing map: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void addLocationMarkers() {
        // Add markers for each location
        addMarker("Thaba Bosiu", 200, 150);
        addMarker("Sehlabathebe", 400, 300);
        addMarker("Maseru", 150, 250);
        addMarker("Katse Dam", 300, 200);
        addMarker("Pioneer Mall", 180, 280);
    }
    
    private void addMarker(String locationName, double x, double y) {
        Button marker = new Button("📍");
        marker.setStyle("-fx-background-color: transparent; -fx-font-size: 24px;");
        marker.setLayoutX(x);
        marker.setLayoutY(y);
        marker.setOnAction(event -> openLocation(locationName));
        markerPane.getChildren().add(marker);
    }
    
    private void openLocation(String locationName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interactive/tour/app/Location.fxml"));
            Parent root = loader.load();
            
            LocationController controller = loader.getController();
            String locationId = getLocationId(locationName);
            controller.setLocationInfo(locationId, locationName);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) mapView.getScene().getWindow();
            stage.setTitle("Lesotho Tour Guide - " + locationName);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println("Error opening location: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private String getLocationId(String locationName) {
        switch (locationName) {
            case "Thaba Bosiu": return "thaba_bosiu";
            case "Sehlabathebe": return "sehlabathebe";
            case "Maseru": return "maseru";
            case "Katse Dam": return "katse_dam";
            case "Pioneer Mall": return "pioneer_mall";
            default: return "";
        }
    }
    
    @FXML
    private void goBackToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interactive/tour/app/Home.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setTitle("Lesotho Tour Guide");
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println("Error returning to home: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 