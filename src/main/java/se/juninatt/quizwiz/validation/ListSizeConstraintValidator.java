package se.juninatt.quizwiz.validation;

import se.juninatt.quizwiz.validation.annotations.FixedSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Custom Validator class for {@link FixedSize} constraint.
 * This validator checks whether a given list's size matches an expected value.
 */
public class ListSizeConstraintValidator implements ConstraintValidator<FixedSize, List<?>> {

    /**
     * The expected size of the list, as defined by the constraint.
     */
    private int expectedSize;

    /**
     * Initializes the validator.
     *
     * @param constraintAnnotation the annotation instance for the constraint
     */
    @Override
    public void initialize(FixedSize constraintAnnotation) {
        this.expectedSize = constraintAnnotation.expectedSize();
    }

    /**
     * Checks if the list is valid according to the constraint.
     *
     * @param list the list to be checked
     * @param constraintValidatorContext context in which the constraint is evaluated
     * @return true if the list's size matches the expected size, otherwise false
     */
    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && list.size() == expectedSize;
    }
}
