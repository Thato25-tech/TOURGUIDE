package controllers;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;

public class LocationController {
    @FXML private MediaView videoView;
    private MediaPlayer videoPlayer;
    private MediaPlayer audioPlayer;

    @FXML
    public void initialize() {
        String videoPath = new File("resources/media/thaba_bosiu.mp4").toURI().toString();
        videoPlayer = new MediaPlayer(new Media(videoPath));
        videoView.setMediaPlayer(videoPlayer);
        videoPlayer.play();
    }

    public void playAudio() {
        String audioPath = new File("resources/media/thaba_bosiu.mp3").toURI().toString();
        audioPlayer = new MediaPlayer(new Media(audioPath));
        audioPlayer.play();
    }

    public void openQuiz() throws Exception {
        Parent quizRoot = FXMLLoader.load(getClass().getResource("/fxml/quiz.fxml"));
        Stage quizStage = new Stage();
        quizStage.setTitle("Thaba-Bosiu Quiz");
        quizStage.setScene(new Scene(quizRoot));
        quizStage.show();
    }
}
