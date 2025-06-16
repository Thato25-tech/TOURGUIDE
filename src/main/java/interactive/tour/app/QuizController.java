package interactive.tour.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizController {
    @FXML private Text txtQuizTitle;
    @FXML private Text txtQuestionTitle;
    @FXML private RadioButton rbAnswer1;
    @FXML private RadioButton rbAnswer2;
    @FXML private RadioButton rbAnswer3;
    @FXML private RadioButton rbAnswer4;
    @FXML private ToggleGroup answerGroup;
    @FXML private Button btnSubmit;
    @FXML private Button btnNextQuestion;
    @FXML private Button btnClose;
    @FXML private Text txtFeedback;
    @FXML private Text txtScore;
    
    private String locationId;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int totalQuestions = 0;
    
    // Maps to store questions for each location
    private static final Map<String, List<Question>> LOCATION_QUESTIONS = new HashMap<>();
    
    static {
        // Initialize questions for each location
        List<Question> thabaBosiu = new ArrayList<>();
        thabaBosiu.add(new Question(
            "Who founded the mountain fortress of Thaba Bosiu?",
            List.of("King Moshoeshoe I", "King Letsie III", "King Leabua Jonathan", "Queen 'Masenate Mohato Seeiso"),
            "King Moshoeshoe I",
            "King Moshoeshoe I founded Thaba Bosiu in 1824 as a mountain stronghold for the Basotho people."
        ));
        thabaBosiu.add(new Question(
            "Why is Thaba Bosiu called the 'Mountain of Night'?",
            List.of(
                "It was believed to grow larger at night and become impossible to conquer",
                "The fortress was only attacked at night",
                "It has dense fog that appears at night",
                "The mountain appears black when viewed at night"
            ),
            "It was believed to grow larger at night and become impossible to conquer",
            "According to legend, the mountain was said to grow larger at night, making it impossible for enemies to climb and conquer it."
        ));
        thabaBosiu.add(new Question(
            "What is the elevation of Thaba Bosiu?",
            List.of("1,804 meters", "2,104 meters", "1,547 meters", "3,482 meters"),
            "1,804 meters",
            "Thaba Bosiu stands at an elevation of 1,804 meters above sea level."
        ));
        LOCATION_QUESTIONS.put("thaba_bosiu", thabaBosiu);
        
        List<Question> sehlabathebe = new ArrayList<>();
        sehlabathebe.add(new Question(
            "In which year was Sehlabathebe National Park established?",
            List.of("1970", "1982", "1995", "2005"),
            "1970",
            "Sehlabathebe National Park was established in 1970 as Lesotho's first national park."
        ));
        sehlabathebe.add(new Question(
            "When was Sehlabathebe declared a UNESCO World Heritage Site?",
            List.of("2013", "2008", "1998", "2020"),
            "2013",
            "It was declared a UNESCO World Heritage Site in 2013 as part of the Maloti-Drakensberg Park."
        ));
        sehlabathebe.add(new Question(
            "What is Sehlabathebe National Park known for?",
            List.of(
                "Unique rock formations and sandstone arches",
                "Large population of lions",
                "The highest waterfall in Africa",
                "Ancient dinosaur fossils"
            ),
            "Unique rock formations and sandstone arches",
            "The park is known for its spectacular landscape of unique rock formations, sandstone arches, and grasslands."
        ));
        LOCATION_QUESTIONS.put("sehlabathebe", sehlabathebe);
        
        List<Question> maseru = new ArrayList<>();
        maseru.add(new Question(
            "What river forms part of the border between Maseru and South Africa?",
            List.of("Caledon River", "Orange River", "Senqu River", "Mohokare River"),
            "Caledon River",
            "The Caledon River (also known as the Mohokare River) forms the western border of Maseru with South Africa."
        ));
        maseru.add(new Question(
            "Which building in Maseru is shaped like the traditional mokorotlo hat?",
            List.of("Basotho Hat building", "Royal Palace", "Parliament Building", "National Museum"),
            "Basotho Hat building",
            "The Basotho Hat building is a landmark in Maseru designed to resemble the traditional mokorotlo hat worn by the Basotho people."
        ));
        maseru.add(new Question(
            "When did Maseru become the capital of Lesotho?",
            List.of("1869", "1905", "1966", "1980"),
            "1869",
            "Maseru was established as the administrative capital by the British in 1869, when Lesotho was under British protection."
        ));
        LOCATION_QUESTIONS.put("maseru", maseru);
        
        List<Question> katseDam = new ArrayList<>();
        katseDam.add(new Question(
            "How tall is the Katse Dam?",
            List.of("185 meters", "150 meters", "210 meters", "175 meters"),
            "185 meters",
            "The Katse Dam stands at 185 meters high, making it Africa's second-largest double-curvature arch dam."
        ));
        katseDam.add(new Question(
            "Which river was dammed to create the Katse Dam?",
            List.of("Malibamat'so River", "Orange River", "Senqu River", "Caledon River"),
            "Malibamat'so River",
            "The Katse Dam was built on the Malibamat'so River, a tributary of the Senqu/Orange River."
        ));
        katseDam.add(new Question(
            "What is the main purpose of the Katse Dam?",
            List.of(
                "To supply water to South Africa and generate hydroelectricity for Lesotho",
                "To prevent flooding in the lowlands",
                "To create a fishing and tourism industry",
                "To irrigate farmland in Lesotho"
            ),
            "To supply water to South Africa and generate hydroelectricity for Lesotho",
            "The primary purpose of the Katse Dam is to supply water to South Africa's Gauteng Province while generating hydroelectricity for Lesotho."
        ));
        LOCATION_QUESTIONS.put("katse_dam", katseDam);
        
        List<Question> pioneerMall = new ArrayList<>();
        pioneerMall.add(new Question(
            "In which city is Pioneer Mall located?",
            List.of("Maseru", "Leribe", "Mafeteng", "Mohale's Hoek"),
            "Maseru",
            "Pioneer Mall is located in Maseru, the capital city of Lesotho."
        ));
        pioneerMall.add(new Question(
            "Which of these features can be found in Pioneer Mall?",
            List.of("Cinema", "Water park", "Ski resort", "Wildlife sanctuary"),
            "Cinema",
            "Pioneer Mall features a cinema along with various shops, restaurants, and other entertainment options."
        ));
        pioneerMall.add(new Question(
            "What type of building is Pioneer Mall?",
            List.of("Shopping center", "Government building", "Historical museum", "Sports arena"),
            "Shopping center",
            "Pioneer Mall is a shopping center that serves as a major retail hub in Lesotho."
        ));
        LOCATION_QUESTIONS.put("pioneer_mall", pioneerMall);
    }
    
    @FXML
    public void initialize() {
        // Will be initialized by setupQuiz method
    }
    
    public void setupQuiz(String locationId) {
        this.locationId = locationId;
        
        // Get questions for this location
        questions = new ArrayList<>(LOCATION_QUESTIONS.getOrDefault(locationId, new ArrayList<>()));
        if (questions.isEmpty()) {
            txtQuestionTitle.setText("No questions available for this location.");
            disableQuizControls();
            return;
        }
        
        // Shuffle questions for randomness
        Collections.shuffle(questions);
        
        // Limit to 5 questions if there are more
        if (questions.size() > 5) {
            questions = questions.subList(0, 5);
        }
        
        totalQuestions = questions.size();
        updateScoreDisplay();
        
        // Load first question
        loadQuestion(0);
    }
    
    private void loadQuestion(int index) {
        if (index >= questions.size()) {
            showQuizResults();
            return;
        }
        
        // Reset UI state
        btnSubmit.setDisable(false);
        btnNextQuestion.setDisable(true);
        txtFeedback.setText("");
        answerGroup.selectToggle(null);
        
        // Load question data
        Question question = questions.get(index);
        txtQuestionTitle.setText(question.getQuestionText());
        
        // Shuffle answer options for randomness
        List<String> options = new ArrayList<>(question.getOptions());
        Collections.shuffle(options);
        
        // Set radio button texts
        rbAnswer1.setText(options.get(0));
        rbAnswer2.setText(options.get(1));
        rbAnswer3.setText(options.get(2));
        rbAnswer4.setText(options.get(3));
        
        // Update title with question number
        txtQuizTitle.setText(String.format("Question %d of %d", index + 1, totalQuestions));
        currentQuestionIndex = index;
    }
    
    @FXML
    public void submitAnswer() {
        RadioButton selectedButton = (RadioButton) answerGroup.getSelectedToggle();
        if (selectedButton == null) {
            txtFeedback.setText("Please select an answer");
            txtFeedback.setFill(Color.ORANGE);
            return;
        }
        
        String selectedAnswer = selectedButton.getText();
        Question currentQuestion = questions.get(currentQuestionIndex);
        
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            txtFeedback.setText("Correct! " + currentQuestion.getExplanation());
            txtFeedback.setFill(Color.GREEN);
            score++;
            updateScoreDisplay();
        } else {
            txtFeedback.setText("Incorrect. " + currentQuestion.getExplanation());
            txtFeedback.setFill(Color.RED);
        }
        
        btnSubmit.setDisable(true);
        btnNextQuestion.setDisable(false);
    }
    
    @FXML
    public void nextQuestion() {
        loadQuestion(currentQuestionIndex + 1);
    }
    
    @FXML
    public void closeQuiz() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    private void showQuizResults() {
        double percentage = (double) score / totalQuestions * 100;
        
        txtQuestionTitle.setText("Quiz Complete!");
        
        // Display appropriate message based on score
        if (percentage >= 80) {
            txtFeedback.setText(String.format("Excellent! You scored %d out of %d (%.1f%%)\n" +
                    "You're an expert on this location!", score, totalQuestions, percentage));
            txtFeedback.setFill(Color.GREEN);
        } else if (percentage >= 60) {
            txtFeedback.setText(String.format("Good job! You scored %d out of %d (%.1f%%)\n" +
                    "You know this location quite well.", score, totalQuestions, percentage));
            txtFeedback.setFill(Color.BLUE);
        } else {
            txtFeedback.setText(String.format("You scored %d out of %d (%.1f%%)\n" +
                    "Explore the location more to learn about it!", score, totalQuestions, percentage));
            txtFeedback.setFill(Color.ORANGE);
        }
        
        // Hide question UI
        rbAnswer1.setVisible(false);
        rbAnswer2.setVisible(false);
        rbAnswer3.setVisible(false);
        rbAnswer4.setVisible(false);
        btnSubmit.setVisible(false);
        btnNextQuestion.setVisible(false);
    }
    
    private void updateScoreDisplay() {
        txtScore.setText(String.format("Score: %d/%d", score, totalQuestions));
    }
    
    private void disableQuizControls() {
        rbAnswer1.setDisable(true);
        rbAnswer2.setDisable(true);
        rbAnswer3.setDisable(true);
        rbAnswer4.setDisable(true);
        btnSubmit.setDisable(true);
        btnNextQuestion.setDisable(true);
    }
    
    // Inner class for quiz questions
    private static class Question {
        private final String questionText;
        private final List<String> options;
        private final String correctAnswer;
        private final String explanation;
        
        public Question(String questionText, List<String> options, String correctAnswer, String explanation) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
            this.explanation = explanation;
        }
        
        public String getQuestionText() {
            return questionText;
        }
        
        public List<String> getOptions() {
            return options;
        }
        
        public String getCorrectAnswer() {
            return correctAnswer;
        }
        
        public String getExplanation() {
            return explanation;
        }
    }
} 