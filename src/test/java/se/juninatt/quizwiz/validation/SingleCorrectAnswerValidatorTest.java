package se.juninatt.quizwiz.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.juninatt.quizwiz.model.entity.AnswerOption;

@DisplayName("SingleCorrectAnswerValidator:")
public class SingleCorrectAnswerValidatorTest {

    private final SingleCorrectAnswerValidator validator = new SingleCorrectAnswerValidator();
    private final ConstraintValidatorContext context = null;

    @Test
    @DisplayName("List with one correct answer is valid")
    public void testValidator_returnsIsValid_whenOneAnswerIsCorrect() {
        boolean isValid = validator.isValid(Arrays.asList(
                new AnswerOption("Text1", 0),
                new AnswerOption("Text2", 1),
                new AnswerOption("Text3", 0),
                new AnswerOption("Text4", 0)
        ), context);
        assertTrue(isValid);
    }

    @Test
    @DisplayName("List with multiple correct answers is invalid")
    public void testValidator_returnsIsNotValid_whenTwoAnswersAreCorrect() {
        boolean isValid = validator.isValid(Arrays.asList(
                new AnswerOption("Text1", 1),
                new AnswerOption("Text2", 1),
                new AnswerOption("Text3", 0),
                new AnswerOption("Text4", 0)
        ), context);
        assertFalse(isValid);
    }

    @Test
    @DisplayName("List with no correct answers is invalid")
    public void testValidator_returnsIsNotValid_whenNoAnswerIsCorrect() {
        boolean isValid = validator.isValid(Arrays.asList(
                new AnswerOption("Text1", 0),
                new AnswerOption("Text2", 0),
                new AnswerOption("Text3", 0),
                new AnswerOption("Text4", 0)
        ), context);
        assertFalse(isValid);
    }

    @Test
    public void testNullList() {
        boolean isValid = validator.isValid(null, context);
        assertFalse(isValid);
    }
}
