package interactive.tour.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LocationController {
    @FXML private MediaView videoView;
    @FXML private Text txtLocationName;
    @FXML private Text txtLocationDescription;
    @FXML private Button btnPlayAudio;
    
    private MediaPlayer videoPlayer;
    private MediaPlayer audioPlayer;
    private String locationId;
    private boolean isAudioPlaying = false;
    
    // Store location information
    private static final Map<String, LocationInfo> LOCATIONS = new HashMap<>();
    
    static {
        // Initialize location data
        LOCATIONS.put("thaba_bosiu", new LocationInfo(
            "Thaba Bosiu - Mountain Fortress",
            "Thaba Bosiu is a sandstone plateau with an elevation of 1,804 meters. It is known as the Mountain of Night " +
            "because it was believed to grow larger at night and was impossible to conquer. It served as the mountain " +
            "fortress for Moshoeshoe I, the founder of the Basotho nation. Today it stands as one of the most important " +
            "historical sites in Lesotho, with ruins of Moshoeshoe's settlement still visible on the plateau.",
            "/media/thaba_bosiu.mp4",
            "/media/thaba_bosiu_audio.mp3"
        ));
        
        LOCATIONS.put("sehlabathebe", new LocationInfo(
            "Sehlabathebe National Park",
            "Sehlabathebe National Park is located in the remote eastern corner of Lesotho. Established in 1970, it is " +
            "characterized by rolling grasslands, wildflowers, and unique rock formations. The park is home to diverse " +
            "wildlife including birds, reptiles, and small mammals. It was declared a UNESCO World Heritage Site in 2013 " +
            "as part of the Maloti-Drakensberg Park Transboundary World Heritage Site.",
            "/media/sehlabathebe.mp4",
            "/media/sehlabathebe_audio.mp3"
        ));
        
        LOCATIONS.put("maseru", new LocationInfo(
            "Maseru - Capital City",
            "Maseru is the capital and largest city of Lesotho. Located on the border with South Africa along the Caledon " +
            "River, it serves as the administrative capital of the country. The city features a mix of modern buildings, " +
            "traditional markets, and government offices. Key attractions include the Royal Palace, Maseru Market, and the " +
            "Basotho Hat building, which is shaped like the traditional mokorotlo hat worn by the Basotho people.",
            "/media/maseru.mp4",
            "/media/maseru_audio.mp3"
        ));
        
        LOCATIONS.put("katse_dam", new LocationInfo(
            "Katse Dam",
            "The Katse Dam is a concrete arch dam on the Malibamat'so River in Lesotho. At 185 meters high, it is Africa's " +
            "second-largest double-curvature arch dam. Part of the Lesotho Highlands Water Project, it supplies water to " +
            "South Africa's industrial Gauteng Province while generating hydroelectricity for Lesotho. The dam's reservoir " +
            "forms a large lake that has become a tourist attraction, offering activities like fishing and boating.",
            "/media/katse_dam.mp4",
            "/media/katse_dam_audio.mp3"
        ));
        
        LOCATIONS.put("pioneer_mall", new LocationInfo(
            "Pioneer Shopping Mall",
            "Pioneer Mall is one of the largest shopping centers in Lesotho, located in the capital city of Maseru. The mall " +
            "features a wide variety of shops, restaurants, and entertainment options. It serves as a major retail hub for " +
            "both locals and visitors, offering international brands alongside local products. The modern complex includes " +
            "a cinema, supermarkets, clothing stores, and banking facilities.",
            "/media/pioneer_mall.mp4",
            "/media/pioneer_mall_audio.mp3"
        ));
    }
    
    @FXML
    public void initialize() {
        // Default initialization - actual content will be set by setLocationInfo
    }
    
    public void setLocationInfo(String locationId, String title) {
        this.locationId = locationId;
        
        LocationInfo info = LOCATIONS.get(locationId);
        if (info != null) {
            txtLocationName.setText(info.getName());
            txtLocationDescription.setText(info.getDescription());
            
            try {
                // Load and play video
                String videoPath = getClass().getResource(info.getVideoPath()).toExternalForm();
                Media videoMedia = new Media(videoPath);
                videoPlayer = new MediaPlayer(videoMedia);
                videoView.setMediaPlayer(videoPlayer);
                videoView.setVisible(true);
                videoPlayer.play();
            } catch (Exception e) {
                System.out.println("Error loading video for " + info.getName() + ": " + e.getMessage());
                videoView.setVisible(false);
            }
        }
    }
    
    @FXML
    public void playVideo() {
        if (videoPlayer != null) {
            videoPlayer.play();
        }
    }
    
    @FXML
    public void pauseVideo() {
        if (videoPlayer != null) {
            videoPlayer.pause();
        }
    }
    
    @FXML
    public void rewindVideo() {
        if (videoPlayer != null) {
            videoPlayer.seek(Duration.ZERO);
            videoPlayer.play();
        }
    }
    
    @FXML
    public void toggleAudio() {
        LocationInfo info = LOCATIONS.get(locationId);
        if (info == null) return;
        
        if (isAudioPlaying && audioPlayer != null) {
            audioPlayer.stop();
            btnPlayAudio.setText("Play Audio Guide");
            isAudioPlaying = false;
        } else {
            try {
                String audioPath = getClass().getResource(info.getAudioPath()).toExternalForm();
                Media audioMedia = new Media(audioPath);
                
                // Stop previous audio if playing
                if (audioPlayer != null) {
                    audioPlayer.stop();
                }
                
                audioPlayer = new MediaPlayer(audioMedia);
                audioPlayer.play();
                btnPlayAudio.setText("Stop Audio Guide");
                isAudioPlaying = true;
                
                // Reset button text when audio finishes
                audioPlayer.setOnEndOfMedia(() -> {
                    btnPlayAudio.setText("Play Audio Guide");
                    isAudioPlaying = false;
                });
            } catch (Exception e) {
                System.out.println("Error playing audio: " + e.getMessage());
                btnPlayAudio.setDisable(true);
            }
        }
    }
    
    @FXML
    public void goBackToHome() {
        try {
            // Stop media
            if (videoPlayer != null) {
                videoPlayer.stop();
            }
            if (audioPlayer != null) {
                audioPlayer.stop();
            }
            
            // Load home screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interactive/tour/app/Home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) videoView.getScene().getWindow();
            stage.setTitle("Lesotho Tour Guide");
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println("Error returning to home: " + e.getMessage());
        }
    }
    
    @FXML
    public void openQuiz() {
        try {
            // Pause media when opening quiz
            if (videoPlayer != null) {
                videoPlayer.pause();
            }
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interactive/tour/app/Quiz.fxml"));
            Parent root = loader.load();
            
            QuizController quizController = loader.getController();
            quizController.setupQuiz(locationId);
            
            Stage quizStage = new Stage();
            quizStage.setTitle("Quiz: " + txtLocationName.getText());
            quizStage.setScene(new Scene(root));
            
            // Set quiz window size
            quizStage.setWidth(800);
            quizStage.setHeight(600);
            quizStage.setResizable(true);
            quizStage.centerOnScreen();
            
            quizStage.show();
        } catch (IOException e) {
            System.out.println("Error opening quiz: " + e.getMessage());
        }
    }
    
    // Inner class to store location information
    private static class LocationInfo {
        private final String name;
        private final String description;
        private final String videoPath;
        private final String audioPath;
        
        public LocationInfo(String name, String description, String videoPath, String audioPath) {
            this.name = name;
            this.description = description;
            this.videoPath = videoPath;
            this.audioPath = audioPath;
        }
        
        public String getName() {
            return name;
        }
        
        public String getDescription() {
            return description;
        }
        
        public String getVideoPath() {
            return videoPath;
        }
        
        public String getAudioPath() {
            return audioPath;
        }
    }
} 