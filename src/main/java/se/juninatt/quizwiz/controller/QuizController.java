package se.juninatt.quizwiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.juninatt.quizwiz.model.entity.Quiz;

/**
 * Controller to manage {@link Quiz}-related routes and views.
 */
@Controller
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    /**
     * Show the welcome page of the quiz application.
     * @param model The model object to pass attributes to the view.
     * @return The name of the Thymeleaf template to render.
     */
    @GetMapping("/quizwiz")
    public String showWelcomePage(Model model) {
        logger.info("Showing welcome page.");
        model.addAttribute("message", "    Welcome to the Quiz Portal. Choose from various quizzes or create your own!");
        return "welcome-page";
    }


    @GetMapping("/create-quiz")
    public String showCreateQuizPage(Model model) {
        model.addAttribute("page_title", "Create Your Own Quiz");
        return "create-quiz-page";
    }
}