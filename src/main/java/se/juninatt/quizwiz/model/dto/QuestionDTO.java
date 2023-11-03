package se.juninatt.quizwiz.model.dto;

import se.juninatt.quizwiz.model.entity.Quiz;
import se.juninatt.quizwiz.validation.annotations.SingleCorrectAnswer;
import se.juninatt.quizwiz.validation.annotations.FixedSize;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 * Data Transfer Object for handling the content of a {@link Quiz}
 */
public record QuestionDTO(
        @NotBlank(message = "Question cannot be blank.")
        @Size(min = 1, max = 255, message = "Question must be between 1 and 255 characters.")
        String questionText,
        @NotNull(message = "Time limit must be added.")
        @Min(value = 1, message = "Time limit must be at least 1 second.")
        @Max(value = 60, message = "Time limit cannot exceed 60 seconds.")
        int timeLimit,        @Min(value = 1, message = "Points must be at least 1.")

        @NotNull(message = "Points for question must be added.")
        @Min(value = 1, message = "Points must be at least 1.")
        @Max(value = 20, message = "Points cannot exceed 20.")
        int points,
        @Valid
        @FixedSize(expectedSize= 4, message = "There must be exactly 4 answer options.")
        @SingleCorrectAnswer(message = "Answer options must have exactly 1 correct option")
        List<AnswerOptionDTO> answerOptions
) {}