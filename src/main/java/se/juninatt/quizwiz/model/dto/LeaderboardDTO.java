package se.juninatt.quizwiz.model.dto;

import se.juninatt.quizwiz.model.entity.Leaderboard;

import java.util.Map;

/**
 * Represents the top results by different criteria.
 */
public record LeaderboardDTO(
    Map<String, Leaderboard> leaders
) {}
