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
import se.juninatt.quizwiz.model.dto.QuizCreationDTO;
import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.service.QuizService;

/**
 * Controller to manage {@link Quiz}-related routes and views.
 */
@Controller
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);
    @Autowired
    private QuizService quizService;

    /**
     * Show the welcome page of the quiz application.
     * @param model The model object to pass attributes to the view.
     * @return The name of the Thymeleaf template to render.
     */
    @GetMapping("/quizwiz")
    public String showWelcomePage(Model model) {
        logger.info("Showing welcome page.");
        model.addAttribute("message", "Welcome to the Quiz Portal. Choose from various quizzes or create your own!");
        return "welcome-page";
    }

    /**
     * Show the create-quiz page.
     * @param model The model object to pass attributes to the view.
     * @return The name of the Thymeleaf template to render.
     */
    @GetMapping("/create-quiz-page")
    public String showCreateQuizPage(Model model) {
        logger.info("Showing create-quiz page.");
        model.addAttribute("page_title", "Create Your Own Quiz");
        return "create-quiz-page";
    }

    @PostMapping("/create-quiz")
    public ResponseEntity<?> createQuiz(@RequestBody QuizCreationDTO quizContent) {
        logger.info("Received quiz submission");
        return ResponseEntity.ok(quizService.createQuiz(quizContent));
    }
}