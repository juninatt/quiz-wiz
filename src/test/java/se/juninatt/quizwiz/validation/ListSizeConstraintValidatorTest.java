package se.juninatt.quizwiz.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.juninatt.quizwiz.validation.annotations.FixedSize;

@DisplayName("ListSizeConstraint-Validator:")
public class ListSizeConstraintValidatorTest {

    private final ConstraintValidatorContext context = null;

    @Test
    @DisplayName("List with four elements is valid when expected size is 4")
    public void testValidator_returnsIsValid_whenListHasFourElements() {
        ListSizeConstraintValidator validator = new ListSizeConstraintValidator();
        validator.initialize(new FixedSizeStub(4));
        boolean isValid = validator.isValid(Arrays.asList(1, 2, 3, 4), context);
        assertTrue(isValid);
    }

    @Test
    @DisplayName("List with three elements is invalid when expected size is 4")
    public void testValidator_returnsIsNotValid_whenListHasThreeElements() {
        ListSizeConstraintValidator validator = new ListSizeConstraintValidator();
        validator.initialize(new FixedSizeStub(4));
        boolean isValid = validator.isValid(Arrays.asList(1, 2, 3), context);
        assertFalse(isValid);
    }

    @Test
    @DisplayName("Null list is invalid when expected size is 4")
    public void testValidator_returnsIsNotValid_whenListIsNull() {
        ListSizeConstraintValidator validator = new ListSizeConstraintValidator();
        validator.initialize(new FixedSizeStub(4));
        boolean isValid = validator.isValid(null, context);
        assertFalse(isValid);
    }

    @Test
    @DisplayName("List with five elements is valid when expected size is 5")
    public void testValidator_returnsIsValid_whenListHasFiveElements() {
        ListSizeConstraintValidator validator = new ListSizeConstraintValidator();
        validator.initialize(new FixedSizeStub(5));
        boolean isValid = validator.isValid(Arrays.asList(1, 2, 3, 4, 5), context);
        assertTrue(isValid);
    }

    @Test
    @DisplayName("List with four elements is invalid when expected size is 5")
    public void testValidator_returnsIsNotValid_whenListHasFourElementsAndExpectedSizeIsFive() {
        ListSizeConstraintValidator validator = new ListSizeConstraintValidator();
        validator.initialize(new FixedSizeStub(5));
        boolean isValid = validator.isValid(Arrays.asList(1, 2, 3, 4), context);
        assertFalse(isValid);
    }

    @Test
    @DisplayName("List with three elements is valid when expected size is 3")
    public void testValidator_returnsIsValid_whenListHasThreeElementsAndExpectedSizeIsThree() {
        ListSizeConstraintValidator validator = new ListSizeConstraintValidator();
        validator.initialize(new FixedSizeStub(3));
        boolean isValid = validator.isValid(Arrays.asList(1, 2, 3), context);
        assertTrue(isValid);
    }

    @Test
    @DisplayName("List with two elements is invalid when expected size is 3")
    public void testValidator_returnsIsNotValid_whenListHasTwoElementsAndExpectedSizeIsThree() {
        ListSizeConstraintValidator validator = new ListSizeConstraintValidator();
        validator.initialize(new FixedSizeStub(3));
        boolean isValid = validator.isValid(Arrays.asList(1, 2), context);
        assertFalse(isValid);
    }


    private record FixedSizeStub(int expectedSize) implements FixedSize {

        @Override
            public String message() {
                return null;
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }
        }
}
