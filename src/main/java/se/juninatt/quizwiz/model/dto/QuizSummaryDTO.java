package se.juninatt.quizwiz.model.dto;

import se.juninatt.quizwiz.model.entity.Question;
import se.juninatt.quizwiz.model.entity.Quiz;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Data Transfer Object representing a summary of a {@link Quiz}.
 * This object includes the topic of the quiz, total score, total time and a list of its {@link Question} objects.
 */
public record QuizSummaryDTO(

        long id,
        @Size(min = 1, max = 50, message = "Topic must be between 1 and 50 characters.")
        String topic,

        @NotNull(message = "Questions cannot be null.")
        List<String> questions,

        @Min(value = 0, message = "Total score cannot be negative.")
        int totalScore,

        @Min(value = 0, message = "Total time cannot be negative.")
        int totalTime
) {
}

