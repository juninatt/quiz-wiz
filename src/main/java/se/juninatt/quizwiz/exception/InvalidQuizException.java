package se.juninatt.quizwiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.juninatt.quizwiz.model.entity.Quiz;

/**
 * Exception thrown when invalid content is used to create a {@link Quiz}.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidQuizException extends RuntimeException {
    /**
     * Constructor for the exception.
     *
     * @param message The message describing the exception.
     */
    public InvalidQuizException(String message) {
        super(message);
    }

    /**
     * Constructor for the exception.
     *
     * @param message The message describing the exception.
     * @param cause   The underlying cause of the exception.
     */
    public InvalidQuizException(String message, Throwable cause) {
        super(message, cause);
    }
}
