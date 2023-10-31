package se.juninatt.quizwiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class representing a bad request.
 * The HTTP status for this exception is set to BAD_REQUEST (400).
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidQuizTopicException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message describing the error.
     */
    public InvalidQuizTopicException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message The detail message describing the error.
     * @param cause   The cause of the exception.
     */
    public InvalidQuizTopicException(String message, Throwable cause) {
        super(message, cause);
    }

}
