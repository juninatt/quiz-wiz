package se.juninatt.quizwiz.validation.annotations;


import se.juninatt.quizwiz.validation.ListSizeConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to check that a list contains a fixed number of elements.
 */
@Constraint(validatedBy = ListSizeConstraintValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FixedSize {
    String message() default "List contains wrong number of elements.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int expectedSize();
}

