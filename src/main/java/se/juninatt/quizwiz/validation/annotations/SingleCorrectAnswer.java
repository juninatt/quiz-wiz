package se.juninatt.quizwiz.validation.annotations;

import se.juninatt.quizwiz.model.entity.AnswerOption;
import se.juninatt.quizwiz.validation.SingleCorrectAnswerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation to mark that a list of {@link AnswerOption} should contain exactly one correct answer.
 */
@Documented
@Constraint(validatedBy = SingleCorrectAnswerValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleCorrectAnswer {
    String message() default "Only one answer option should be correct";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
