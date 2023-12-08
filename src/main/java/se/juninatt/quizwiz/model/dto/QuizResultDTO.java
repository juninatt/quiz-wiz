package se.juninatt.quizwiz.model.dto;

import se.juninatt.quizwiz.model.entity.Quiz;

/**
 * Record that summarizes a {@link Quiz} round finished by a user before an ID has been added.
 * Holds the name entered by the player, the total score, the total time it took for the user to answer all questions and the ID of the quiz.
 */
public record QuizResultDTO(
        long quizId,
        String player,
        int score,
        int timeUsedSec
) { }
