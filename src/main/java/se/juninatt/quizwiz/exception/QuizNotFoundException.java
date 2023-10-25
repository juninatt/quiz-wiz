package se.juninatt.quizwiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.juninatt.quizwiz.model.entity.Quiz;

/**
 * Custom exception class to represent a {@link Quiz} not found error.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuizNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public QuizNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message describing the error.
     * @param cause   the cause of the exception.
     */
    public QuizNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
