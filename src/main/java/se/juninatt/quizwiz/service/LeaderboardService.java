package se.juninatt.quizwiz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.juninatt.quizwiz.model.entity.Leaderboard;
import se.juninatt.quizwiz.repository.LeaderboardRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing operations related to a {@link Leaderboard}.
 * This service provides methods to retrieve leaderboards based on various criteria.
 */
@Service
public class LeaderboardService {

    private final int defaultLength = 20;
    private final int upperLength = 30;
    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }


    public List<Leaderboard> getLeaderBoardByTimePercentage() {
        Pageable topTwenty = PageRequest.of(0, defaultLength, Sort.by("timeUsedPercentage").ascending());
        Page<Leaderboard> page = leaderboardRepository.findAllByOrderByTimeUsedPercentageAsc(topTwenty);
        return page.getContent();
    }

    public List<Leaderboard> getLeaderboardByScore() {
        List<Leaderboard> gameSummaries = leaderboardRepository.findAll();

        return gameSummaries.stream()
                .sorted(Comparator.comparingInt(this::extractScore).reversed())
                .limit(defaultLength)
                .collect(Collectors.toList());
    }

    public List<Leaderboard> getMostRecentGames() {
        return leaderboardRepository.findAll().stream()
                .sorted(Comparator.comparing(Leaderboard::getDate).reversed())
                .limit(upperLength)
                .collect(Collectors.toList());
    }


    // Private helper methods


    private int extractScore(Leaderboard leaderboard) {
        try {
            String scoreString = leaderboard.getScore();
            String gameScorePart = scoreString.split("/")[0];
            return Integer.parseInt(gameScorePart);
        } catch (Exception e) {

            return 0;
        }
    }
}
