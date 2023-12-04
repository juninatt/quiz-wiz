package se.juninatt.quizwiz.model.dto;

import se.juninatt.quizwiz.model.entity.Quiz;

/**
 * Record that summarizes a {@link Quiz} round finished by a user before an ID has been added.
 */
public record LeaderboardDTO(
        String player,
        String topic,
        String score,
        int totalScorePercentage,
        int timeUsedPercentage,
        String date,
        long quizId
) { }
