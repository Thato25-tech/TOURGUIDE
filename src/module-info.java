module interactive.tour.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    
    opens interactive.tour.app to javafx.fxml;
    exports interactive.tour.app;
} 