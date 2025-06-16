package interactive.tour.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/interactive/tour/app/Home.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
            primaryStage.setTitle("Interactive Tour App");
        primaryStage.setScene(scene);
        
        // Set initial size
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        
        // Make stage resizable
        primaryStage.setResizable(true);
        
        // Center on screen
        primaryStage.centerOnScreen();
        
        primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error starting application: " + e.getMessage());
            Platform.exit();
            System.exit(1);
        }
    }
    
    public static void main(String[] args) {
        try {
            // Set JavaFX properties
            System.setProperty("javafx.verbose", "true");
            System.setProperty("javafx.platform", "desktop");
            
            // Launch the application
        launch(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to launch JavaFX application: " + e.getMessage());
            System.exit(1);
        }
    }
}
