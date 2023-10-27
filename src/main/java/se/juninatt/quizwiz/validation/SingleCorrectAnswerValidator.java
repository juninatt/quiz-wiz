package se.juninatt.quizwiz.validation;

import se.juninatt.quizwiz.model.entity.AnswerOption;
import se.juninatt.quizwiz.validation.annotations.SingleCorrectAnswer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Validates that only one {@link AnswerOption} is marked as the correct answer.
 * Implements the ConstraintValidator interface for custom validation logic.
 */
public class SingleCorrectAnswerValidator implements ConstraintValidator<SingleCorrectAnswer, List<AnswerOption>> {

    /**
     * Initializes the validator.
     *
     * @param constraintAnnotation The annotation instance for a given element.
     */
    @Override
    public void initialize(SingleCorrectAnswer constraintAnnotation) {}

    /**
     * Checks if the list of {@link AnswerOption} contains exactly one correct answer.
     *
     * @param answerOptions The list of answer options.
     * @param context The constraint validator context.
     * @return true if exactly one answer is correct, false otherwise.
     */
    @Override
    public boolean isValid(List<AnswerOption> answerOptions, ConstraintValidatorContext context) {
        if (answerOptions == null) {
            return false;
        }

        long correctAnswerCount = answerOptions.stream()
                .filter(AnswerOption::isCorrectAnswer)
                .count();

        return correctAnswerCount == 1;
    }
}
