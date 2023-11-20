package se.juninatt.quizwiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.juninatt.quizwiz.model.entity.Leaderboard;
import se.juninatt.quizwiz.service.LeaderboardService;

import java.util.List;

/**
 * The LeaderboardController class handles all web requests for  managing all {@link Leaderboard} objects in the database.
 * It utilizes the {@link LeaderboardService} for interactions with {@link Leaderboard} data and constructs
 * the model for the view templates.
 */
@Controller
@RequestMapping("/leaderboard")
public class LeaderboardController {
    private static final Logger logger = LoggerFactory.getLogger(LeaderboardController.class);
    @Autowired
    private LeaderboardService leaderboardService;


    /**
     * Handles GET requests to fetch and display the {@link Leaderboard} based on scores.
     * Retrieves a list of leaderboard entries sorted by score and adds it to the model for view rendering.
     *
     * @param model The Spring {@link Model} to hold leaderboard data.
     * @return The view template name for displaying the leaderboard.
     */
    @GetMapping("/score")
    public String getLeaderboardByScore(Model model) {
        logger.info("Received request to load leaderboard by score");
        List<Leaderboard> leaderboard = leaderboardService.getLeaderboardByScore();
        model.addAttribute("leaderboard", leaderboard);

        return "menu/leaderboard";
    }

    @PostMapping("save")
    public ResponseEntity<?> saveQuizGameToLeaderboard(Leaderboard leaderboard) {


        return ResponseEntity.ok(null);
    }
}
