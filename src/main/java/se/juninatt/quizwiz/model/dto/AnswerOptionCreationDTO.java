package se.juninatt.quizwiz.model.dto;

import se.juninatt.quizwiz.model.entity.AnswerOption;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object for creating a {@link AnswerOption}.
 */
public record AnswerOptionCreationDTO(
        @NotBlank(message = "Option text cannot be blank.")
        @Size(min = 1, max = 255, message = "Answer must be between 1 and 255 characters.")
        String optionText,

        @NotNull(message = "Must assign a correct answer.")
        boolean isCorrectAnswer
) {}
