package lesotho.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class QuizController {

    @FXML
    private RadioButton rbAnswer1;
    @FXML
    private RadioButton rbAnswer2;
    @FXML
    private RadioButton rbAnswer3;
    @FXML
    private Button btnSubmit;

    private ToggleGroup toggleGroup;

    @FXML
    private void initialize() {
        toggleGroup = new ToggleGroup();
        rbAnswer1.setToggleGroup(toggleGroup);
        rbAnswer2.setToggleGroup(toggleGroup);
        rbAnswer3.setToggleGroup(toggleGroup);
    }

    @FXML
    private void handleSubmit() {
        RadioButton selectedAnswer = (RadioButton) toggleGroup.getSelectedToggle();
        String answer = selectedAnswer.getText();
        String correctAnswer = "Thaba Bosiu"; // Replace with the correct answer for the current quiz

        if (answer.equals(correctAnswer)) {
            showAlert("Correct!", "Well done! You chose the correct answer.", AlertType.INFORMATION);
        } else {
            showAlert("Incorrect!", "Sorry, that's not the right answer. Try again!", AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
