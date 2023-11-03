package se.juninatt.quizwiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.juninatt.quizwiz.model.dto.QuizDTO;
import se.juninatt.quizwiz.model.dto.QuizSummaryListDTO;
import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.service.QuizService;

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
        logger.info("Showing welcome page.");

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
        logger.info("Loading 'create-quiz-page'.");

        model.addAttribute("page_title", "Create Your Own Quiz");

        return "menu-pages/create-quiz-page";
    }

    /**
     * Displays a list of available {@link Quiz}zes for the user to play.
     *
     * @param model The model object to pass data for displaying available quizzes.
     * @return The name of the Thymeleaf template to display quiz selections.
     */
    @GetMapping("/quiz-selection")
    public String showPlayQuizPage(Model model) {
        logger.info("Showing view quizzes page");
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
        logger.info("Received quiz submission, loading 'create-quiz-page'.");
        quizService.createQuiz(quizContent);

        return ResponseEntity.ok("Quiz successfully created");
    }
}