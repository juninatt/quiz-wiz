package se.juninatt.quizwiz.service;

import org.springframework.stereotype.Service;
import se.juninatt.quizwiz.model.entity.GameSummary;
import se.juninatt.quizwiz.repository.GameSummaryRepository;

import java.util.List;

/**
 * Service class responsible for managing operations related to a {@link GameSummary}.
 * This service provides methods to retrieve leaderboards based on various criteria.
 */
@Service
public class GameSummaryService {
    private final GameSummaryRepository gameSummaryRepository;

    public GameSummaryService(GameSummaryRepository gameSummaryRepository) {
        this.gameSummaryRepository = gameSummaryRepository;
    }


    /**
     * Retrieves the leaderboard based on time percentage used by players.
     *
     * @return A list of GameSummary entities, sorted by time used percentage in ascending order.
     */
    public List<GameSummary> getLeaderBoardByTimePercentage() {
        return gameSummaryRepository.findAllByOrderByTimeUsedPercentageAsc();
    }

    /**
     * Retrieves the leaderboard based on completion percentage of game rounds.
     *
     * @return A list of GameSummary entities, sorted by completion percentage in descending order.
     */
    List<GameSummary> getLeaderBoardByCompletionPercentage() {
        return gameSummaryRepository.findAllByOrderByCompletionPercentageDesc();
    }

    /**
     * Retrieves the leaderboard based on total scores achieved by players.
     *
     * @return A list of GameSummary entities, sorted by total score in descending order.
     */
    List<GameSummary> getLeaderBoardByTotalScore() {
        return gameSummaryRepository.findAllByOrderByTotalScoreDesc();
    }
}
