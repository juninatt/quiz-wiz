package se.juninatt.quizwiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.juninatt.quizwiz.mapper.QuizMapper;
import se.juninatt.quizwiz.model.dto.QuestionDTO;
import se.juninatt.quizwiz.model.dto.QuizDTO;
import se.juninatt.quizwiz.model.dto.QuizSummaryListDTO;
import se.juninatt.quizwiz.model.entity.GameSummary;
import se.juninatt.quizwiz.model.entity.Question;
import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.service.QuizService;

import java.util.HashMap;
import java.util.Map;

/**
 * The QuizController class handles all web requests for creating, managing,
 * and playing quizzes.
 * It utilizes the {@link QuizService} for interactions with {@link Quiz} data and constructs
 * the model for the view templates.
 */
@Controller
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);
    @Autowired
    private QuizService quizService;

    /**
     * Renders the welcome page with a welcome message.
     *
     * @param model The model object that holds data for the view template.
     * @return The name of the Thymeleaf template to be rendered as a welcome page.
     */
    @GetMapping("/quiz-wiz")
    public String showWelcomePage(Model model) {
        logger.info("Received request to open welcome page");

        model.addAttribute("message", "Welcome to the Quiz Portal. Choose from various quizzes or create your own!");

        return "menu-pages/welcome-page";
    }

    /**
     * Shows the page for creating a new {@link Quiz}.
     *
     * @param model The model object to pass data needed for rendering the page.
     * @return The name of the Thymeleaf template for creating a new quiz.
     */
    @GetMapping("/quiz-creation")
    public String showCreateQuizPage(Model model) {
        logger.info("Received request to open quiz-creation page");

        model.addAttribute("page_title", "Create Your Own Quiz");

        return "menu-pages/quiz-creation-page";
    }

    /**
     * Displays a list of available {@link Quiz}zes for the user to play.
     *
     * @param model The model object to pass data for displaying available quizzes.
     * @return The name of the Thymeleaf template to display quiz selections.
     */
    @GetMapping("/quiz-selection")
    public String showPlayQuizPage(Model model) {
        logger.info("Received request to show quiz selection");
        QuizSummaryListDTO quizSummaries = quizService.getQuizSummaryList();

        model.addAttribute("quizzes", quizSummaries);
        model.addAttribute("pageTitle", "Available Quizzes:");
        model.addAttribute("subTitle", "Click on the quiz you want to play!");

        return "menu-pages/quiz-selection-page";
    }

    /**
     * Handles the creation of a new {@link Quiz}.
     *
     * @param quizContent The content of the quiz from the request body.
     * @return A ResponseEntity indicating the outcome of the create operation.
     */
    @PostMapping("/create-quiz")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizContent) {
        logger.info("Received request to create new quiz " + quizContent);
        quizService.createQuiz(quizContent);

        return ResponseEntity.ok("Quiz successfully created");
    }

    /**
     * Starts a new quiz game for the given quiz ID and prepares the view.
     *
     * @param quizId the ID of the quiz to start.
     * @param model the {@link Model} to hold attributes for the view template.
     * @return the name of the template to render the quiz game.
     */
    @GetMapping("/start-quiz/{quizId}")
    public String startQuizGame(@PathVariable long quizId, Model model) {
        logger.info("Received request to start quiz:" + quizId);

        model.addAttribute("quizId", quizId);
        model.addAttribute("topic", quizService.getQuizById(quizId).getTopic());

        return "quiz-game";
    }

    /**
     * Fetches the next question from the quiz corresponding to the given indices.
     *
     * @param quizId        the ID of the quiz.
     * @param questionIndex the index of the question within the quiz.
     * @return a {@link ResponseEntity} containing the question DTO or an error message.
     */
    @GetMapping("/next-question/{quizId}/question/{questionIndex}")
    public ResponseEntity<?> getQuestionFromQuiz(@PathVariable long quizId, @PathVariable int questionIndex) {
        logger.info("Received request for next question");

        Question question = quizService.getQuestionFromQuiz(questionIndex, quizId);
        QuestionDTO questionContent = QuizMapper.INSTANCE.entityToDTO(question);

        return ResponseEntity.ok(questionContent);
    }

    /**
     * Processes a quiz submission. Accepts a quiz ID and a {@link GameSummary} object, logs the submission,
     * and returns a response with the player's score.
     *
     * @param quizId       the ID of the submitted quiz
     * @param gameSummary  the summary of the player's performance
     * @return a {@link ResponseEntity} with submission confirmation and score
     */
    @PostMapping("/submit-quiz/{quizId}")
    public ResponseEntity<?> submitQuiz(@PathVariable long quizId, @RequestBody GameSummary gameSummary) {
        logger.info("Quiz submission received: " + gameSummary);
        Map<String, Object> response = new HashMap<>();

        gameSummary.setQuiz(quizService.getQuizById(quizId));

        response.put("message", "Quiz submission received");
        response.put("score", gameSummary.getTotalScore());

        return ResponseEntity.ok(response);
    }
}