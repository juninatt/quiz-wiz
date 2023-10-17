package se.juninatt.quizwiz.model.dto;

import se.juninatt.quizwiz.model.entity.GameSummary;

import java.util.Map;

/**
 * Represents the top results by different criteria.
 */
public record LeaderboardDTO(
    Map<String, GameSummary> leaders
) {}
