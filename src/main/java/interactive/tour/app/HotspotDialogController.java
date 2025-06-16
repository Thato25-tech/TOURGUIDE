package interactive.tour.app;

import interactive.tour.app.model.Hotspot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class HotspotDialogController {
    @FXML
    private Label lblDescription;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button btnPlayAudio;
    @FXML
    private Button btnPlayVideo;
    @FXML
    private Button btnQuiz;

    private Hotspot hotspot;

    public void setHotspot(Hotspot hs) {
        this.hotspot = hs;
        lblDescription.setText(hs.getDescription());
    }

    @FXML
    private void playAudio() {
        if (hotspot != null && hotspot.getAudioPath() != null) {
            MediaPlayer player = new MediaPlayer(new Media(getClass().getResource(hotspot.getAudioPath()).toExternalForm()));
            player.play();
        }
    }

    @FXML
    private void playVideo() {
        if (hotspot != null && hotspot.getVideoPath() != null) {
            MediaPlayer player = new MediaPlayer(new Media(getClass().getResource(hotspot.getVideoPath()).toExternalForm()));
            mediaView.setMediaPlayer(player);
            player.play();
        }
    }

    @FXML
    private void openQuiz() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/QuizDialog.fxml"));
            Pane quizPane = loader.load();
            
            Stage quizStage = new Stage();
            quizStage.setTitle("Quiz - " + hotspot.getName());
            quizStage.setScene(new Scene(quizPane));
            quizStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 