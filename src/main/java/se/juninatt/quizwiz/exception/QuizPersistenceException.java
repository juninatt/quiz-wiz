package se.juninatt.quizwiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.juninatt.quizwiz.model.entity.Quiz;

/**
 * This exception is thrown when there is an error related to {@link Quiz} persistence in the application.
 * The HTTP status for this exception is set to UNPROCESSABLE_ENTITY (422).
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class QuizPersistenceException extends RuntimeException{

    /**
     * Constructs a new QuizPersistenceException with the specified detail message.
     *
     * @param message The detail message.
     */
    public QuizPersistenceException(String message) {
        super(message);
    }

    /**
     * Constructs a new QuizPersistenceException with the specified detail message and cause.
     *
     * @param message The detail message of the exception.
     * @param cause The cause of the exception.
     */
    public QuizPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
