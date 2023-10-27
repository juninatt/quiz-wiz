package se.juninatt.quizwiz.model.dto;

import se.juninatt.quizwiz.model.entity.Quiz;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Data Transfer Object for creating a {@link Quiz}.
 */
public record QuizCreationDTO (
        @NotBlank(message = "Topic cannot be blank.")
        @Size(min = 1, max = 50, message = "Topic must be between 1 and 255 characters.")
        String topic,
        @Valid
        @NotNull(message = "Questions cannot be null.")
        List<QuestionCreationDTO> questions
) {}