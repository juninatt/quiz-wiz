package se.juninatt.quizwiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.juninatt.quizwiz.model.entity.Leaderboard;
import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.service.LeaderboardService;
import se.juninatt.quizwiz.service.QuizService;

/**
 * Controller for main navigation pages in the Quiz Wiz application.
 * Handles routing to the welcome, quiz creation, quiz selection, and leaderboard pages,
 * utilizing {@link QuizService} and {@link LeaderboardService} for data retrieval.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private QuizService quizService;
    @Autowired
    private LeaderboardService leaderboardService;


    /**
     * Handles GET request to render the welcome page.
     *
     * @return The view template name for displaying the welcome page.
     */
    @GetMapping("/welcome")
    public String loadWelcomeTemplate() {
        logger.info("Received request to load welcome template");

        return "menu/welcome";
    }


    /**
     * Handles GET request to show the {@link Quiz} creation page.
     *
     * @return The view template name for displaying the quiz creation page.
     */
    @GetMapping("/quiz-creation")
    public String loadQuizCreationTemplate() {
        logger.info("Received request to load quiz creation template");
        return "menu/quiz-creation";
    }


    /**
     * Handles GET request to display available {@link Quiz} games.
     *
     * @param model The Spring {@link Model} to hold data for available quizzes.
     * @return The view template name for displaying available quizzes.
     */
    @GetMapping("/quiz-selection")
    public String loadQuizSelectionTemplate(Model model) {
        logger.info("Received request to load quiz selection template");
        model.addAttribute("quizzes", quizService.getQuizSummaryList());

        return "menu/quiz-selection";
    }

    /**
     * Handles GET request to load the {@link Leaderboard}.
     * Retrieves and adds the most recent game leaderboard data to the model.
     *
     * @param model The Spring {@link Model} to hold leaderboard data.
     * @return The view template name ("menu/leaderboard") for displaying the leaderboard.
     */
    @GetMapping("/leaderboard")
    public String loadLeaderboardTemplate(Model model) {
        logger.info("Received request to load leaderboard template");
        model.addAttribute("leaderboard", leaderboardService.getMostRecentGames());

        return "menu/leaderboard";
    }
}
